<script>
  import Card, {Content, PrimaryAction, Media, MediaContent, Actions, ActionButtons, ActionIcons} from '@smui/card';

  import Search from "../components/Search.svelte";

  let tags=[];

      async function fetchArticles() {
        const res = await fetch('http://localhost:8080/article/start', {
            method: 'GET',
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (!res.ok) {
            throw new Error(await res.text());
        }

        return await res.json();
    }
</script>

<svelte:head>
  <title>Search</title>
</svelte:head>

<Search style="width=500px"tags={tags}/>

<div class="card-deck">
  {#await fetchArticles()}
    <div>Loading articles...</div>
  {:then articles}
    {#each articles as article}
      <Card class="card">
        <Content><a href="{article.url}">{article.name}</a></Content>      
      </Card>
    {/each}
  {:catch error}
    <div class="error">Error loading articles: {error.message}</div>
  {/await}
</div>

<style>
  .card-deck {
    display: flex;
    flex-flow: row wrap;
    justify-content: space-between;
    align-items: stretch;
    background-color: #f8f8f8;
  }

  * :global(.card) {
    width: 200px;
  	height: 200px;
	  margin: 10px;
	  flex-grow: 1;
  }
</style>
