<script>
  import Chip, {Set, Text} from '@smui/chips';
  import Textfield from '@smui/textfield';
  import Icon from '@smui/textfield/icon/index';
  import Button, {Label} from '@smui/button';
  import {Icon as CommonIcon} from '@smui/common';
  
  export let tags = [];
  let newTag = '';
</script>

<Textfield variant="outlined" withTrailingIcon bind:value={newTag}>
    <Set chips={tags} let:chip input>
        <Chip>
            <Text>{chip}</Text>
            <CommonIcon class="material-icons" trailing tabindex="0">cancel</CommonIcon>
        </Chip>
    </Set>
    <Icon class="material-icons" trailing>search</Icon>
</Textfield>
