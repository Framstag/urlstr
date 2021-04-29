<script>
    import Chip, {Set, Checkmark, Text} from '@smui/chips';
    import {Icon as CommonIcon} from '@smui/common';

    export let possibleTags = [];
    export let selectedTags = [];
</script>

<Set chips={possibleTags} 
    bind:selected={selectedTags}
    let:chip filter>
    <Chip tabindex="0">
        <Checkmark />
        <Text>{chip.name}</Text>
    </Chip>
</Set>

