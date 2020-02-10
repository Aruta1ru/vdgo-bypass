<template>
    <tr>
             <td style="text-align: center;">{{ bypass.id }}</td>
             <td style="text-align: left;">{{ bypass.executor.name }}</td>
             <td style="text-align: left;">{{ bypass.address.addr }}</td>
             <td style="text-align: center;">{{ bypass.dogType }}</td>
             <td style="text-align: center;">{{ bypass.bypassDate }}</td>
             <td style="text-align: center;">{{ bypass.doneType }}</td>
             <td style="text-align: center; margin:auto"><input type="button" class="buttonDone" value="Выполнено" @click="edit" /></td>
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

td {
font-family: Helios, sans-serif;
padding: 10px;
border: 1px solid black;
font-stretch: condensed;
font-weight: bold;
}

tr:hover {
    background: yellow; /* Цвет фона при наведении */
    color: black; /* Цвет текста при наведении */
   }

.buttonDone {
background-color:white;
font-family:Helios,sans-serif;
border: solid 1px black;
padding:5px;
}

</style>