import Vue from 'vue'

const removeFiles = Vue.resource('/files/obj-delete{/fileId}');

export default {
    delete:  fId => removeFiles.delete({fileId: fId}),
}