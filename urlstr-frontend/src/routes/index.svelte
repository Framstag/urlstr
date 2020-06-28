<script>
  import Card, {Content, PrimaryAction, Media, MediaContent, Actions, ActionButtons, ActionIcons} from '@smui/card';
  import DataTable, {Head, Body, Row, Cell} from '@smui/data-table';

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

<div>
  <Search tags={tags}/>
</div>

<div class="card-deck">
  {#await fetchArticles()}
    <div>Loading articles...</div>
  {:then articles}
    {#each articles as article}
    <Card style="width: 320px;">
      <Content>{article.name}</Content>      
    </Card>
    {/each}
  {:catch error}
    <div class="error">Error loading articles: {error.message}</div>
  {/await}
</div>

<style>
  .card-deck {
    /*display: inline-flex;
    justify-content: center;
    align-items: center;
    min-height: 500px;
    min-width: 380px;
    background-color: #f8f8f8;
    margin-right: 20px;
    margin-bottom: 20px;*/
    display: flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
  }
</style>
