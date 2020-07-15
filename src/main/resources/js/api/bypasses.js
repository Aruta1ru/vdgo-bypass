import Vue from 'vue'

const bypassOne = Vue.resource('/bypass{/id}')

export default {
    done: (bypass, doneType, undoneReason) =>
     bypassOne.save({id: bypass.id},
                               {
                               idObject: bypass.address.id,
                               idExecutor: bypass.executor.id,
                               dateAction: bypass.bypassDate,
                               doneType: doneType,
                               undoneReason: undoneReason,
                               created: bypass.created}),

}