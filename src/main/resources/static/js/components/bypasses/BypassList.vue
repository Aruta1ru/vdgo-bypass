
<template>
    <table style="width: 80%;">
        <th style="border: 2px solid red;">ID</th>
        <th style="border: 2px solid red;">Исполнитель</th>
        <th style="border: 2px solid red;">Адрес</th>
        <th style="border: 2px solid red;">Тип договора</th>
        <th style="border: 2px solid red;">Дата обхода</th>
        <th style="border: 2px solid red;">Статус выполнения</th>
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
</style>