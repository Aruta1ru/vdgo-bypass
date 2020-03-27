<template>
<v-container>
    <v-dialog v-model="dialog"  scrollable max-width="300px">
      <v-card>
        <v-card-title>Укажите причину</v-card-title>
        <v-divider></v-divider>
        <v-card-text style="height: 300px;">
          <v-radio-group v-model="dialogm1" column>
            <v-radio label="Нет дома"></v-radio>
            <v-radio label="Не пустили"></v-radio>
            <v-radio label="Газ отключен"></v-radio>
            <v-radio label="Нет денег для оплаты работ"></v-radio>
            <v-radio label="Нет доступа к газопроводу, газоиспользующему оборудованию"></v-radio>
            <v-radio label="Несоответствие оборудования, указанному в договоре"></v-radio>
            <v-radio label="Смена абонента"></v-radio>
            <v-radio label="Неадекватный абонент"></v-radio>
            <v-radio label="Отсутствие собственника (нанимателя) на объекте"></v-radio>
          </v-radio-group>
          </v-card-text>
        <v-divider></v-divider>
                <v-card-actions>
                  <v-btn color="primary" text @click="dialog = false">Сохранить</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
      </v-dialog>

  <v-data-table
    :headers="headers"
    :items="bypassRows"
    :single-expand="singleExpand"
    :expanded.sync="expanded"
    :search="search"
    @click:row="expandRow"
    item-key="id"
    class="elevation-1"
    hide-default-footer
    disable-pagination
  >

    <template v-slot:top>
      <v-toolbar flat>
<v-toolbar-title > Заявки </v-toolbar-title>
<v-divider
          class="mx-4"
          inset
          vertical
        ></v-divider>
    <v-spacer> </v-spacer>
     <v-text-field
                          v-model="search"
                          :append-icon="searchIcon"
                          label="Поиск"
                          single-line
                          hide-details
                          class="mx-5"
                        >
                        </v-text-field>
      </v-toolbar>
    </template>

    <template v-slot:expanded-item="{ headers, item }">
    <td :colspan="headers.length">

    <v-container>
    <table>
    <tr> <th> ID </th> <td> {{item.id}} </td> </tr>
    <tr> <th> Дата обхода </th> <td> {{ item.bypassDate}} </td> </tr>
    <tr> <th> ФИО абонента </th> <td> {{item.clientInfo.name}} </td> </tr>
    <tr> <th> Телефон </th> <td> {{ item.clientInfo.phone }} </td> </tr>
    <tr> <th> Тип договора </th> <td> {{item.dogType}} </td> </tr>
    </table>
         </v-container>

         <v-divider> </v-divider>

     <v-container v-if="item.fileList.length > 0">
     <v-container v-for="file in item.fileList" :key="file.id" >

                 <table>
                 <td width="auto">
                 <h4> {{ file.name }}  {{ parseFloat(file.size/1024).toFixed(2) }} КБайт </h4>
                 </td>

                 <td width="35px">
                 <v-btn small color="primary" @click=downloadFile(file)>
                       <v-icon> {{downloadIcon}} </v-icon>
                       </v-btn>
                       </td>

                 <td width="35px" > <v-btn small color="red" @click=deleteFile(file.id)>
                <v-icon> {{deleteIcon}}  </v-icon>
                 </v-btn>
                 </td>
                 </table>
                 </v-container>
                 </v-container>

         <v-divider> </v-divider>

 <v-container>
 <v-row>
                    <v-flex  xs11
                             sm7
                             md4  >

                      <v-file-input
                       v-model="files"
                       color="primary"
                       multiple
                       placeholder="Выберите файлы"
                       :prepend-icon="attachFilesIcon"
                       outlined
                       dense
                       accept="image/*"
                       @change="uploadFiles(item)"
                     >
                     </v-file-input>
                     </v-flex>
                     </v-row>
 </v-container>
    </td>
        </template>

    <template v-slot:item.doneType="{ item }">
          <v-chip :color="getColor(item.doneType)" dark>{{ item.doneType }}</v-chip>
        </template>

    <template v-slot:item.actionDone="{ item }">
              <v-btn :disabled=getBtnStatus(item.doneType) small color="primary" @click.native.stop=setExec(item)>
                        <v-icon>{{doneIcon}}</v-icon>
              </v-btn>
              </template>

      <template v-slot:item.actionUndone="{ item }">
                   <v-btn :disabled=getBtnStatus(item.doneType) small color="red" @click.native.stop ="dialog = true">
                             <v-icon>{{undoneIcon }}</v-icon>
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

          function    findOne(list, id) {
             for (let i = 0; i < list.length; i++ ) {
                  if (list[i].id === id) {
                                        return list[i]
                                    }
                                }
                                return null;
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

  function getFileInfo(data) {
     let arr = {};
          arr.id = data.id;
          arr.name = data.name;
          arr.size = data.size;
     return arr;
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
                           arr.doneType=list[i].doneType ? "выполнено" : "не выполнено";
                           arr.dogType=list[i].dogType;
                           arr.objectId=list[i].address.id;
                           arr.clientInfo=list[i].address.client;
                           arr.fileList=list[i].address.files;

                        tableList.push(arr);
                        arr = {};
                      }

                        return tableList;
                      }

import bypassesApi from 'api/bypasses'
import removeFilesApi from 'api/removeFiles'
import {mdiMagnify, mdiCloudUpload, mdiCloudDownload, mdiDelete, mdiCheck, mdiPaperclip,  mdiClose} from '@mdi/js'
  export default {
    data () {
      return {
        searchIcon: mdiMagnify,
        cloudUploadIcon: mdiCloudUpload,
        downloadIcon: mdiCloudDownload,
        deleteIcon: mdiDelete,
        doneIcon: mdiCheck,
        undoneIcon: mdiClose,
        attachFilesIcon: mdiPaperclip,
        expanded: [],
        files: [],
        search: '',
        dialogm1: '',
        dialog: false,
        singleExpand: true,
        headers: [
          { text: 'Адрес', value: 'address' },
          { text: 'Дата обхода', value: 'bypassDate' },
          { text: 'Статус выполнения', value: 'doneType' },
          { text: '', value: 'actionDone', sortable: false },
          { text: '', value: 'actionUndone', sortable: false }
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
                                 bypassesApi.done(bypass).then(result =>
                                      result.json().then(data => {
                                          const index = getIndex(frontendData.bypasses, data.id)
                                          frontendData.bypasses.splice(index, 1, data);
                                          const indexTable = this.bypassRows.indexOf(item);
                                          this.bypassRows.splice(indexTable, 1, findOne(getTableData(frontendData.bypasses), data.id));
                                      })
                                  );
                             },

                downloadFile(file) { window.location.href = '/files/obj-download/'+ file.id;},

                deleteFile(fId) {
                            removeFilesApi.delete(fId).then(result => {
                            if (result.ok) {
                            let arr = findIndexByFileId(this.bypassRows, fId);
                            this.bypassRows[arr.rowIndex].fileList.splice(arr.fileIndex, 1);
                            }
                            })
                      },

                uploadFiles(item) {
                            if (this.files) {
                                     let formData = new FormData();
                                     for (let file of this.files) {formData.append("files", file, file.name);}
                                     this.$resource('/files/obj-upload-multiple{/objId}').save({objId: item.objectId}, formData).then(result =>
                                     {if (result.ok) {
                                                result.json().then(data => {
                                                const indexTable = this.bypassRows.indexOf(item);
                                                for (let i = 0; i < data.length; i++) {
                                                let arr = getFileInfo(data[i]);
                                                this.bypassRows[indexTable].fileList.push(arr);
                                                   }
                                                }
                                                )
                                                }
                                                }
                                                );
                                     this.files = [];

                 }
                 },

                expandRow (value) {this.expanded = value === this.expanded[0] ? [] : [value]}
                    }
        }
</script>

<style>

<style>