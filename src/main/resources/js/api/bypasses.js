import Vue from 'vue'

const bypasses = Vue.resource('/bypass/action{/id}')


export default {
    done: bypass => bypasses.save({}, {bypass})
    undone: bypass => bypasses.save({}, {bypass})
}