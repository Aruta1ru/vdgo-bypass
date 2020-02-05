<template>
    <tr>
         <td style="padding: 5px 3px; text-align: center;  border: 2px solid black;">{{ bypass.id }}</td>
         <td style="padding: 5px 3px; text-align: left;  border: 2px solid black;">{{ bypass.executor.name }}</td>
         <td style="padding: 5px 3px; text-align: left;  border: 2px solid black;">{{ bypass.address.addr }}</td>
         <td style="padding: 5px 3px; text-align: center;  border: 2px solid black;">{{ bypass.dogType }}</td>
         <td style="padding: 5px 3px; text-align: center;  border: 2px solid black;">{{ bypass.bypassDate }}</td>
         <td style="padding: 5px 3px; text-align: center;  border: 2px solid black;">{{ bypass.doneType }}</td>
         <td style="padding: 5px 3px; text-align: left;"><input type="button" value="Выполнено" @click="edit" /></td>
    </tr>
</template>

<script>

    function getIndex(list, id) {
            for (var i = 0; i < list.length; i++ ) {
                if (list[i].id === id) {
                    return i
                }
            }
            return -1
        }

    export default {
        props: ['bypass', 'bypasses'],
        methods: {
                   edit: function() {
                        this.$resource('/bypass{/id}').update({id: this.bypass.id}, {executor: this.bypass.executor,
                                        address: this.bypass.address, dogType: this.bypass.dogType,
                                        bypassDate: this.bypass.bypassDate, doneType: 1}).then(result =>
                            result.json().then(data => {
                                const index = getIndex(this.bypasses, data.id)
                                this.bypasses.splice(index, 1, data)
                            })
                        );
                   }
             }
    }
</script>

<style>
</style>