<template>

<v-container>

    <v-simple-table>
    <template v-slot:default>
    <thead><v-btn icon @click="showSortBypasses"> <v-icon> sort </v-icon> </v-btn>
    <thead>
        <th class="text-left">ID</th>
        <th class="text-left">Исполнитель</th>
        <th class="text-left">Адрес</th>
        <th class="text-left">Тип договора</th>
        <th class="text-left">Дата обхода</th>
        <th class="text-left">Статус выполнения</th>
        <th></th>
        </thead>
        <tbody>
        <bypass-row v-for="bypass in bypasses" :key="bypass.id" :bypass="bypass" :bypasses="bypasses"/>
        </tbody>

    </template>
    </v-simple-table>
    </v-container>
</template>

<script>
    import BypassRow from 'components/bypasses/BypassRow.vue'
    export default {

        components: {
            BypassRow
        },
        data: function() {
            return {

                  bypasses: frontendData.bypasses
            }
            },

        computed: {
                    sortedBypasses() {
                        return this.bypasses.sort((a,b) => -(a.doneType > b.doneType))
                    }
                    },

         methods: {
         showSortBypasses () {
         return this.sortedBypasses
         },
    }
    }
</script>

<style>
</style>