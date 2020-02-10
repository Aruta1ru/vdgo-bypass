
<template>
    <table class="table-bypass">
            <th>ID</th>
            <th>Исполнитель</th>
            <th>Адрес</th>
            <th>Тип договора</th>
            <th>Дата обхода</th>
            <th>Статус выполнения</th>
            <bypass-row v-for="bypass in bypasses" :key="bypass.id" :bypass="bypass" :bypasses="bypasses" />
     </table>
</template>

<script>
    import BypassRow from 'components/bypasses/BypassRow.vue'
    export default {
        props: ['bypasses'],
        components: {
            BypassRow,
        },
        data: function() {
            return {
                 bypass: null
            }
        }
    }
</script>

<style>

/*Bypass Table - таблица данных */
.table-bypass {
border-collapse:separate;
margin: auto;
width:100%;
font-size:17px;
}

.table-bypass th {
border: 1px solid black;
color:black;
padding: 5px;
font-family: Helios, sans-serif;
}

</style>