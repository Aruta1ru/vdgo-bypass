<template>
<v-container>
  <v-data-table
    :headers="headers"
    :items="bypassRows"
    :single-expand="singleExpand"
    :expanded.sync="expanded"
    :search="search"
    item-key="id"
    class="elevation-1"
    show-expand
    hide-default-footer
   disable-pagination
  >
    <template v-slot:top>
      <v-toolbar flat>
         <v-text-field
                v-model="search"
                append-icon="search"
                label="Поиск"
                single-line
                hide-details
             >
              </v-text-field>
        <v-spacer></v-spacer>
      </v-toolbar>
    </template>

    <template v-slot:expanded-item="{ headers, item }">
    <td :colspan="headers.length">

    <v-container>
         <v-row> ID : {{item.id}} </v-row>
         <v-row> Тип договора: {{item.dogType}} </v-row>
         <v-row> Дата обхода: {{ item.bypassDate}} </v-row>
         </v-container>

         <v-divider> </v-divider>

<v-container v-if="item.fileList.length > 0">

                 <v-container v-for="file in item.fileList" :key="file.id">

                 <table>

                 <td width="auto"> <h4> {{ file.name }}  {{ parseFloat(file.size/1024).toFixed(2) }} КБайт </h4> </td>

                 <td width="40px"> <v-btn small color="primary" @click=downloadFile(file)>
                       <v-icon> cloud_download </v-icon>
                                  </v-btn></td>

                 <td width="40px" > <v-btn small color="red" @click=deleteFile(file.id)>
                <v-icon> delete  </v-icon>
                 </v-btn> </td>
                 </table>
                 </v-container>
                 </v-container>

         <v-divider> </v-divider>

  <v-container>
                   <v-row>
                   <v-flex  xs10
                            sm7
                            md4
>
                  <v-file-input
                      v-model="files"
                      color="primary"
                      counter
                      label="Приложить файлы"
                      multiple
                      placeholder="Выберите файлы"
                      prepend-icon="mdi-paperclip"
                      outlined
                      :show-size="1000"
                    >
                      <template v-slot:selection="{ index, text }">
                        <v-chip
                          v-if="index < 2"
                          color="primary"
                          dark
                          label
                          x-small
                        >
                          {{ text }}
                        </v-chip>

                        <span
                          v-else-if="index === 2"
                          class="overline grey--text text--darken-3 mx-2"
                        >
                          +{{ files.length - 2 }} Файл(ов)
                        </span>
                      </template>
                    </v-file-input>

                    </v-flex>
 <v-flex  xs5
                            sm6
                            md3
>
                    <v-col>
                     <v-btn small color="primary" @click="uploadFiles(item)">
                     <v-icon>cloud_upload</v-icon>
                     </v-btn>
                     </v-col>
                     </v-flex>
                     </v-row>
                    </v-container>




    </td>

        </template>

    <template v-slot:item.doneType="{ item }">
          <v-chip :color="getColor(item.doneType)" dark>{{ item.doneType }}</v-chip>
        </template>

    <template v-slot:item.action="{ item }">
              <v-btn :disabled=getBtnStatus(item.doneType) small color="primary" @click=setExec(item)>
                        <v-icon>done</v-icon>
              </v-btn>
    </template>

  </v-data-table>
  </v-container>
</template>

<script>

  function getIndex(list, id) {
              for (let i = 0; i < list.length; i++ ) {
                  if (list[i].id === id) {
                      return i
                  }
              }
              return -1
          }

  function findIndexByFileId(list, id) {
              let indexes = {};
              if (list.length > 0) {
              for (let i = 0; i < list.length; i++) {
                  if (list[i].fileList.length > 0) {
                  for (let j = 0; j < list[i].fileList.length; j++) {
                            if (list[i].fileList[j].id === id) {
                                indexes.rowIndex = i;
                                indexes.fileIndex = j;
                                return indexes;
                            }
                        }
                  }
              }
              }
              return null;
  }

  function    getTableData(list) {
                      let moment = require('moment');
                      let bpDate = '';
                      let arr = {};
                      moment.locale('ru');

                      let tableList = [];
                      for (let i = 0; i < list.length; i++ ) {
                        bpDate = moment(list[i].bypassDate, 'DD-MM-YYYY HH:mm:ss');

                           arr.id=list[i].id;
                           arr.address= list[i].address.addr;
                           arr.bypassDate= bpDate.format('DD MMMM YYYY');
                           arr.doneType=list[i].doneType;
                           arr.dogType=list[i].dogType;
                           arr.objectId=list[i].address.id;
                           arr.fileList=list[i].address.files;
                        tableList.push(arr);
                        arr = {};
                      }

                        return tableList;
                      }

   function    findOne(list, id) {
   for (let i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
                              return list[i]
                          }
                      }
                      return null;
   }

   function getFileInfo(data) {
   let arr = {};
        arr.id = data.id;
        arr.name = data.name;
        arr.size = data.size;
   return arr;
   }


  export default {
    props: ['bypasses'],
    data () {
      return {
        expanded: [],
        files: [],
        search: '',
        singleExpand: true,
        headers: [
          {
            text: 'ID',
            align: 'start',
            sortable: false,
            value: 'id',
          },
          { text: 'Адрес', value: 'address' },
          { text: 'Дата обхода', value: 'bypassDate' },
          { text: 'Статус выполнения', value: 'doneType' },
          { text: '', value: 'action', sortable: false },
          { text: '', value: 'data-table-expand' },
        ],
        bypassRows: getTableData(frontendData.bypasses),
      }
    },
    methods: {

          getColor (doneType) {
            if (doneType === 'не выполнено') return 'red'
            else return 'green'
          },
          getBtnStatus (doneType) {
           if (doneType === 'не выполнено') return false
                        else return true
          },
          setExec(item) {

                                let bypass = frontendData.bypasses[getIndex(frontendData.bypasses, item.id)];
                                  this.$resource('/bypass{/id}').update({id: bypass.id}, {executor: bypass.executor,
                                                  address: bypass.address, dogType: bypass.dogType,
                                                  bypassDate: bypass.bypassDate, doneType: 1}).then(result =>
                                      result.json().then(data => {
                                          const index = getIndex(frontendData.bypasses, data.id)
                                          frontendData.bypasses.splice(index, 1, data);

                                          const indexTable = this.bypassRows.indexOf(item);
                                          this.bypassRows.splice(indexTable, 1, findOne(getTableData(frontendData.bypasses), data.id));

                                      })
                                  );

                             },

               downloadFile(file) {
                window.location.href = '/files/obj-download/'+ file.id;

               },

                deleteFile(fId) {
                            this.$resource('/files/obj-delete{/fileId}').delete({fileId: fId});
                            // TODO Add status.ok condition
                            let arr = findIndexByFileId(this.bypassRows, fId);
                            this.bypassRows[arr.rowIndex].fileList.splice(arr.fileIndex, 1);
                      },

                 uploadFiles(item) {
                            if (this.files) {
                                    let formData = new FormData();

                                    // files
                                    for (let file of this.files) {
                                        formData.append("files", file, file.name);
                                    }
                                     this.$resource('/files/obj-upload-multiple{/objId}').save({objId: item.objectId}, formData).then(result =>
                                                result.json().then(data => {

                                                const indexTable = this.bypassRows.indexOf(item);
                                                for (let i = 0; i < data.length; i++) {
                                                let arr = getFileInfo(data[i]);
                                                this.bypassRows[indexTable].fileList.push(arr);
                                                   }
                                                }));
                                     this.files = [];
                                     // TODO Add status.ok condition


                 }
                 }
        }
  }
</script>


