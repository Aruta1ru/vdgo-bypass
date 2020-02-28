<template>
    <tr>
         <td>{{ bypass.id }}</td>
         <td>{{ bypass.executor.name }}</td>
         <td>{{ bypass.address.addr }}</td>
         <td>{{ bypass.dogType }}</td>
         <td>{{ bypass.bypassDate }}</td>
         <td>{{ bypass.doneType }}</td>
         <td><v-btn small color="primary" @click="edit">
          <v-icon>done</v-icon>
          </v-btn> </td>
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