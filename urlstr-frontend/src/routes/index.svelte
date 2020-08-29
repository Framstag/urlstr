<script>
  import _ from 'lodash';
  import {onMount} from 'svelte';

  import Card, {Content, PrimaryAction, Media, MediaContent, Actions, ActionButtons, ActionIcons} from '@smui/card';
  import Textfield from '@smui/textfield';
  import Icon from '@smui/textfield/icon/index';

  import ArticleCard from "../components/ArticleCard.svelte"
  import TagChooser from "../components/TagChooser.svelte"

  let searchText = "";
  let selectedTags=[];

  let tagPromise = Promise.resolve([]);
  let articlePromise = Promise.resolve([]);
  let lastLoadedTags = null;

  onMount(async() => {
    tagPromise=fetchTags();
    articlePromise=fetchArticles(searchText,selectedTags);
  });

  $: reloadOnTagsChange(selectedTags);

  async function fetchArticles(searchText,tags) {
    var url = new URL('http://localhost:8080/article/search')

    console.log(`Search: '${searchText}' Tags: '${tagsToTagNames(tags)}'`);

    var urlParams = new URLSearchParams();

    if (searchText) {
      urlParams.append('search',searchText);
    }

    if (tags) {
      // https://stackoverflow.com/questions/38797509/passing-array-into-urlsearchparams-while-consuming-http-call-for-get-request
      tags.forEach(tag => urlParams.append('tags',tag.name));
    }

    url.search = urlParams;

    const res = await fetch(url, {
        method: 'GET',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        }
    });

    if (res.ok && res.status==200) {
      return res.json();
    }

    throw new Error(await res.text());
  }

  async function fetchTags() {
    var url = new URL('http://localhost:8080/tag/start')

    const res = await fetch(url, {
        method: 'GET',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        }
    });

    if (res.ok && res.status==200) {
      return res.json();
    }

    throw new Error(await res.text());
  }

  function reloadOnSearchTextChange() {
      articlePromise = fetchArticles(searchText,selectedTags);
  }

  function reloadOnTagsChange(tags) {
    if (!_.isEqual(tags,lastLoadedTags)) {
      articlePromise = fetchArticles(searchText,tags);

      lastLoadedTags = [...tags];
    }
  }

  function tagsToTagNames(tags) {
    return tags.map(tag => tag.name);
  }

  function searchKeypressHandler(e) {
    if (e.keyCode == 13) {
      reloadOnSearchTextChange();
    }
  }

</script>

<svelte:head>
  <title>Search</title>
</svelte:head>

<!--<Search style="width=500px"tags={tags}/>-->

<Textfield type="text" variant="outlined" withTrailingIcon bind:value={searchText} on:keypress={searchKeypressHandler}>
    <Icon class="material-icons" trailing>search</Icon>
</Textfield>

{#await tagPromise}
  <div>Loading tags...</div>
{:then tags}
  <TagChooser
    possibleTags={tags}
    bind:selectedTags={selectedTags} />
{:catch error}
  <div class="error">Error loading tags: {error.message}</div>
{/await}

<div class="card-container">
  {#await articlePromise}
    <div>Loading articles...</div>
  {:then articles}
    {#each articles as article}
      <ArticleCard {article} />
    {/each}
  {:catch error}
    <div class="error">Error loading articles: {error.message}</div>
  {/await}
</div>

<style>
  .card-container {
    display: flex;
    flex-flow: row wrap;
    justify-content: space-between;
    align-items: stretch;
    background-color: #f8f8f8;
    width: 840px;
  }
</style>
