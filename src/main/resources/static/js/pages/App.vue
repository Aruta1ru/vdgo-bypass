<template>
    <div>
        <div v-if="!profile">Для работы с приложением необходимо <a href="/login">авторизоваться</a></div>
        <div v-else>
            <div>{{profile.name}}&nbsp;<a href="/logout">Выйти</a></div>
            <bypass-list :bypasses="bypasses" />
        </div>
    </div>
</template>

<script>
        import BypassList from 'components/bypasses/BypassList.vue'

        export default {
            components: {
                    BypassList
            },
            data()  {
                return  {
                        bypasses: frontendData.bypasses,
                        profile: frontendData.profile
                }
            }
        }
</script>

<style>

</style>