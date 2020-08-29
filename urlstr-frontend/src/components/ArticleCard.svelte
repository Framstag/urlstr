<script>
  import Card, {Content, PrimaryAction, Media, MediaContent, Actions, ActionButtons, ActionIcons} from '@smui/card';
  import Chip, {Set, Checkmark, Text} from '@smui/chips';
  import IconButton, {Icon} from '@smui/icon-button';

  export let article;

  console.log(article.tags);
</script>

<Card id="card" style="width: 260px; height: 200px; margin: 10px; padding: 16px" variant="raised">
    <PrimaryAction on:click={() => window.open(`${article.url}`)} tagindex="0">
        <Content class="mdc-typography--subtitle1">
            {article.name}

            <Set chips={article.tags} let:chip>
                <Chip tabindex="0">
                    <Text>{chip}</Text>
                </Chip>
            </Set>
        </Content>
    </PrimaryAction>


    <Actions fullBleed>
        <ActionIcons>
            <IconButton class="material-icons" on:click={() => console.log(`Edit ${article.id}`)} title="Edit">edit</IconButton>
            <IconButton class="material-icons" on:click={() => window.open(`${article.url}`)} title="Open in new">open_in_new</IconButton>
        </ActionIcons>
    </Actions>
</Card>
