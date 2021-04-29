<script>
  import TopAppBar, {Row, Section, Title as AppTitle} from '@smui/top-app-bar';
  import Drawer, {AppContent, Content, Header, Title as DrawerTitle, Subtitle, Scrim} from '@smui/drawer';
  import IconButton from '@smui/icon-button';
  import List, {Item, Graphic, Separator, Text} from '@smui/list';
 
  let drawer;
  let drawerOpen=false;
  
  let page = 'Search';
  let title;

  $: title = titleForPage(page);

  function setPage(newPage) {
    page=newPage;
    drawerOpen=false;
  }

  function titleForPage(page) {
    switch (page) {
      case 'Search':
        return 'Search';
      case 'Tags':
        return 'Tags';
      case 'About':
        return 'About';
      default:
        return '???';  
    }
  }

</script>

<style>
  #content {
    max-width: 56em;
    padding: 2em;
    margin: 0 auto;
    box-sizing: border-box;
  }
</style>

<TopAppBar variant="static">
  <Row>
    <Section>
      <IconButton on:click={() => drawerOpen = !drawerOpen} class="material-icons">menu</IconButton>
      <AppTitle>{title}</AppTitle>
    </Section>
  </Row>
</TopAppBar>

<Drawer variant="modal" bind:this={drawer} bind:open={drawerOpen}>
  <Header>
    <DrawerTitle>URLStr</DrawerTitle>
    <Subtitle>Bookmark everything</Subtitle>
  </Header>
  <Content>
    <List>
      <Item href="/" on:click={() => setPage('Search')} activated={page === 'Search'}>
        <Graphic class="material-icons" aria-hidden="true">search</Graphic>
        <Text>Search</Text>
      </Item>
      <Item href="/tags" on:click={() => setPage('Tags')} activated={page === 'Tags'}>
        <Graphic class="material-icons" aria-hidden="true">label</Graphic>
        <Text>Tags</Text>
      </Item>
      <Item href="/about" on:click={() => setPage('About')} activated={page === 'About'}>
        <Graphic class="material-icons" aria-hidden="true">info</Graphic>
        <Text>About</Text>
      </Item>
    </List>
  </Content>
</Drawer>

<Scrim />

<div id="content" class="mdc-typography">
  <slot/>
</div>
