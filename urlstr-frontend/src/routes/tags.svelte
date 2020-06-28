<script>
    import DataTable, {Head, Body, Row, Cell} from '@smui/data-table';

    async function fetchTags() {
        const res = await fetch('http://localhost:8080/tag/start', {
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
	<title>Tags</title>
</svelte:head>

<h1>Tags</h1>

{#await fetchTags()}
  <div>Loading tags...</div>
{:then tags}
<DataTable table$aria-label="Tags">
<Head>
    <Row>
      <Cell>Name</Cell>
      <Cell>Use count</Cell>
    </Row>
  </Head>
  <Body>
  {#each tags as tag}
  <Row>
      <Cell>{tag.name}</Cell>
      <Cell numeric>{tag.useCount}</Cell>
    </Row>
    {/each}
  </Body>
</DataTable>
{:catch error}
  <div class="error">Error loading tags: {error.message}</div>
{/await}

