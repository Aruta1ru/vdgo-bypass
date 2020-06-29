<template>
<v-container>
    <v-dialog v-model="dialog"  scrollable max-width="400px">
      <v-card>
        <v-card-title>Укажите причину</v-card-title>
        <v-divider></v-divider>
        <v-card-text style="height: 300px ">
            <v-radio-group name="rgReason" v-model="dialogm1" column>
            <v-container v-if="undoneReasons.length > 0">
            <v-container v-for="reason in undoneReasons" :key="reason.id" >
            <v-radio :value="reason.id" :label="reason.shortName"></v-radio>
            </v-container>
            </v-container>
            </v-radio-group>
          </v-card-text>
        <v-divider></v-divider>
                <v-card-actions>
                  <v-btn color="primary" text @click="setExec(currentItem, 2, getCheckedValue()); dialog = false">Сохранить</v-btn>
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
    <h4>{{bypassRows.length}} адресов на сегодня </h4>
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
    <v-expansion-panels
    popout
    :focusable="focusable">
    <v-expansion-panel>
    <v-expansion-panel-header>Информация о клиенте</v-expansion-panel-header>
    <v-expansion-panel-content>
    <table>
    <tr> <th> ФИО абонента </th> <td> {{item.clientInfo.name}} </td> </tr>
    <tr> <th> Телефон </th> <td> {{ item.clientInfo.phone }} </td> </tr>
    <tr> <th> Тип договора </th> <td> {{item.dogType}} </td> </tr>
    </table>
    </v-expansion-panel-content>
    </v-expansion-panel>
    <v-expansion-panel>
            <v-expansion-panel-header>Прикрепленные изображения</v-expansion-panel-header>
            <v-expansion-panel-content>

              <v-container v-if="item.fileList.length > 0">
                 <v-container v-for="file in item.fileList" :key="file.id" >
        <table>
        <tr>
        <td width="auto">
         <h5> {{ file.name | truncate(20, '...') }}  {{ parseFloat(file.size/1024).toFixed(2) }} КБайт </h5> </td>
           <td width="5px">
           <v-menu offset-y>
                 <template v-slot:activator="{ on, attrs }">
                   <v-btn
                   fab
                   x-small
                     color="primary"
                     dark
                     v-bind="attrs"
                     v-on="on"
                   >
                     <v-icon>
                       {{ editIcon}}
                     </v-icon>

                   </v-btn>
                 </template>
                 <v-list>
                   <v-list-item
                     @click=downloadFile(file)
                   >
                     <v-list-item-title>Скачать</v-list-item-title>
                   </v-list-item>
                                  <v-list-item
                                    @click=deleteFile(file.id)
                                  >
                                    <v-list-item-title>Удалить</v-list-item-title>
                                  </v-list-item>


                 </v-list>
               </v-menu>
               </td>
        </tr>
        </table>
      </v-container>
      </v-container>

          <v-container>
           <v-row>
           <v-flex  xs11
                    sm7
                    md4  >

                <v-file-input
                 v-model="files"
                 color="primary"
                 multiple
                 placeholder="Выберите изображения"
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
            </v-expansion-panel-content>
            </v-expansion-panel>
    </v-expansion-panels>
         </v-container>
         <v-divider> </v-divider>


    </td>
        </template>

    <template v-slot:item.doneType="{ item }">
          <v-chip :color="getColor(item.doneType)" dark>{{ item.doneType }}</v-chip>
        </template>

    <template v-slot:item.actionDone="{ item }">
              <v-btn  fab depressed :disabled=getDoneBtnStatus(item.doneType) small color="green" @click.native.stop="setExec(item, 1, 0)">
                        <v-icon>{{doneIcon}}</v-icon>
              </v-btn>
              </template>

      <template v-slot:item.actionUndone="{ item }">
                   <v-btn  fab depressed :disabled=getUndoneBtnStatus(item.doneType) small color="red" @click.native.stop ="setCurrentItem(item), dialog=true">
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
                           arr.doneType=list[i].doneType;
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
import {mdiMagnify, mdiCloudUpload, mdiCloudDownload, mdiDelete, mdiCheck, mdiPaperclip, mdiPencil,  mdiClose} from '@mdi/js'
  export default {
    data () {
      return {
        searchIcon: mdiMagnify,
        cloudUploadIcon: mdiCloudUpload,
        downloadIcon: mdiCloudDownload,
        deleteIcon: mdiDelete,
        doneIcon: mdiCheck,
        undoneIcon: mdiClose,
        editIcon: mdiPencil,
        attachFilesIcon: mdiPaperclip,
        expanded: [],
        focusable: true,
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
        undoneReasons: frontendData.undoneReasons,
        currentItem: {},
        checkedValue: 0,
      }
    },
    methods: {

                getColor (doneType) {
                    switch (doneType) {
                      case 'в работе':
                        return 'primary';
                        break;
                      case 'выполнено':
                        return 'green';
                        break;
                      case 'не выполнено':
                        return 'red';
                        break;
                      default:
                        return 'black';
                    }
                },

                setCurrentItem(item) {
                    this.currentItem = item;
                },

                getDoneBtnStatus (doneType) {
                    if (doneType === 'в работе' || doneType === 'не выполнено')  return false
                        else return true
                },

                getUndoneBtnStatus (doneType) {
                    if (doneType === 'в работе' || doneType === 'выполнено') return false
                        else return true
                },

                 getCheckedValue() {
                    let radioBtns = document.getElementsByName("rgReason");

                    for (let i = 0; i < radioBtns.length; i++) {
                        if (radioBtns[i].checked) return radioBtns[i].value;
                    }
                  },

                setExec(item, isDone, reason) {
                                let bypass = frontendData.bypasses[getIndex(frontendData.bypasses, item.id)];

                                 bypassesApi.done(bypass, isDone, reason).then(result =>
                                     {
                                      result.json().then(data => {
                                          const indexBypass = getIndex(frontendData.bypasses, data.id);
                                          frontendData.bypasses.splice(indexBypass, 1, data);
                                          const indexTable = this.bypassRows.indexOf(item);
                                          this.bypassRows.splice(indexTable, 1, findOne(getTableData(frontendData.bypasses), data.id));
                                      });
                                      },
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

                expandRow (value) {this.expanded = value === this.expanded[0] ? [] : [value]},
},

                filters: {
                                                             truncate: function (name, length, suffix) {
                                                                 if (name.length > length) {
                                                                     return name.substring(0, length) + suffix;
                                                                 } else {
                                                                     return name;
                                                                 }
                                                             }
                                                         }





        }
</script>

<style>

<style>
