import Vue from 'vue'

const bypasses = Vue.resource('/bypass{/id}')

export default {
    done: (bypass, doneType, undoneReason) =>
     bypasses.save({id: bypass.id},
                               {
                               idObject: bypass.address.id,
                               idExecutor: bypass.executor.id,
                               dateAction: bypass.bypassDate,
                               doneType: doneType,
                               undoneReason: undoneReason,
                               created: bypass.created})

}