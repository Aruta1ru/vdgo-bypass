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
    class="elevation-0"
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
    <td :colspan="headers.length"
    >
    <v-container>
    <v-expansion-panels
    v-model="panels"
    :focusable="focusable"
    accordion>
    <v-expansion-panel >
    <v-expansion-panel-header>Информация о клиенте</v-expansion-panel-header>
    <v-expansion-panel-content>
    <h4 class="pa-md-4"> Тип договора: {{item.dogType}} </h4>
    <v-divider></v-divider>
    <v-container v-if="!item.clientList===null">
    <table v-for="client in item.clientList" :key="item.clientList.id">
    <tr> <th> ФИО абонента </th> <td> {{client.name}} </td> </tr>
    <tr> <th> Телефон </th> <td> {{ client.phone }} </td> </tr>
    <v-divider></v-divider>
    </table>
    </v-container>
    <v-container v-else>
    <h4 class="pa-md-4"> Данные о клиенте отсутствуют </h4>
    </v-container>
    </v-expansion-panel-content>
    </v-expansion-panel>
    <v-expansion-panel >
        <v-expansion-panel-header>Оборудование</v-expansion-panel-header>
        <v-expansion-panel-content>
       <table v-if="item.equipmentList">
           <tr>
           <th> Наименование оборудования </th>
           <th> Кол-во </th>
           <th> Доля </th>
           <th> Дата установки </th>
           <th> Дата отключения </th>
           </tr>
           <tr v-for="equipment in item.equipmentList" :key=item.equipmentList.id>
           <td> {{equipment.name}} </td>
           <td> {{equipment.quantity}} </td>
           <td> {{roundDouble(equipment.part)}} </td>
           <td> {{getFormattedDate(equipment.installDate)}} </td>
           <td> {{getFormattedDate(equipment.shutdownDate)}}</td>
           </tr>
           </table>
        </v-expansion-panel-content>
        </v-expansion-panel>

    <v-expansion-panel @click=getFilesPhoto(item)>
    <v-expansion-panel-header>Фотографии объекта</v-expansion-panel-header>
    <v-expansion-panel-content v-bind:value="valuePhotoData" >
                                                    <v-carousel  hide-delimiters height="auto" :show-arrows="true">
                                                      <v-carousel-item
                                                         v-for="(item,i) in items"
                                                         :key="i"
                                                            >
                                                       <v-card max-width="700"
                                                               class="mx-auto">
                                                        <v-img contain :src="item.src" height="380" aspect-ratio="1.77"></v-img>
                                                        <v-card-actions>
                                                              <v-btn
                                                              @click=downloadFile(item.id)
                                                                color="blue"
                                                                x-small
                                                                fab
                                                              >
                                                               <v-icon>
                                                               {{downloadIcon}}
                                                               </v-icon>

                                                              </v-btn>
                                                              <v-btn
                                                              @click=deleteFilePhoto(item.id)
                                                                color="red"
                                                                x-small
                                                                fab
                                                              >
                                                              <v-icon> {{deleteIcon}} </v-icon>

                                                              </v-btn>
                                                            </v-card-actions>
                                                         </v-card>
                                                       </v-carousel-item>
                                                     </v-carousel>

                                                     <v-container>
                                                                <v-row>
                                                                <v-flex  xs11
                                                                         sm7
                                                                         md4  >

                                                                     <h2 class="pa-md-2">Загрузить: </h2>

                                                                     <v-file-input
                                                                      v-model="files"
                                                                      color="primary"
                                                                      multiple
                                                                      placeholder="Выберите изображения"
                                                                      :prepend-icon="attachFilesIcon"
                                                                      outlined
                                                                      dense
                                                                      accept="image/*"
                                                                      @change="uploadFilesPhoto(item)"
                                                                      rounded
                                                                                                >
                                                                       </v-file-input>

                                                                       </v-flex>
                                                                            </v-row>
                                                                            </v-container>

                                                </v-expansion-panel-content>
                                    </v-expansion-panel>

      <v-expansion-panel @click=getFilesITD(item)>
          <v-expansion-panel-header>Исполнительная техническая документация</v-expansion-panel-header>
          <v-expansion-panel-content v-bind:value="valueITDData">
                                                          <v-carousel  hide-delimiters height="auto" :show-arrows="true">
                                                             <v-carousel-item
                                                               v-for="(item,i) in items"
                                                               :key="i"
                                                                  >
                                                             <v-card max-width="700"
                                                                     class="mx-auto">
                                                              <v-img contain :src="item.src" height="380" aspect-ratio="1.77"></v-img>
                                                              <v-card-actions>
                                                                    <v-btn
                                                                    @click=downloadFile(item.id)
                                                                      color="blue"
                                                                      x-small
                                                                      fab
                                                                    >
                                                                     <v-icon>
                                                                     {{downloadIcon}}
                                                                     </v-icon>

                                                                    </v-btn>
                                                                    <v-btn
                                                                    @click=deleteFileITD(item.id)
                                                                      color="red"
                                                                      x-small
                                                                      fab
                                                                    >
                                                                    <v-icon> {{deleteIcon}} </v-icon>

                                                                    </v-btn>
                                                                  </v-card-actions>
                                                               </v-card>
                                                             </v-carousel-item>
                                                           </v-carousel>

                                                           <v-container>
                                                                      <v-row>
                                                                      <v-flex  xs11
                                                                               sm7
                                                                               md4  >

                                                                            <h2 class="pa-md-2">Загрузить: </h2>

                                                                           <v-file-input
                                                                            v-model="files"
                                                                            color="primary"
                                                                            multiple
                                                                            placeholder="Выберите изображения"
                                                                            :prepend-icon="attachFilesIcon"
                                                                            outlined
                                                                            dense
                                                                            accept="image/*"
                                                                            @change="uploadFilesITD(item)"
                                                                            rounded
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
          arr.fileType = data.fileType;
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
                           arr.clientList=list[i].address.client;
                           arr.fileList=list[i].address.files;
                           arr.equipmentList=list[i].address.equipment;
                           arr.reason=list[i].undoneReason.shortName;
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
      items: [],
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
        panels: '',
        dialog: false,
        singleExpand: true,
        headers: [
          { text: 'Адрес', value: 'address' },
          { text: 'Дата обхода', value: 'bypassDate' },
          { text: 'Статус выполнения', value: 'doneType' },
          { text:'Причина невыполнения', value: 'reason'},
          { text: '', value: 'actionDone', sortable: false },
          { text: '', value: 'actionUndone', sortable: false },

        ],
        bypassRows: getTableData(frontendData.bypasses),
        undoneReasons: frontendData.undoneReasons,
        currentItem: {},
        checkedValue: 0,
        valuePhotoData: {
        value: 0
        },
        valueITDData: {
        value: 1
        },
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
                downloadFile(fId) { window.location.href = '/files/obj-download/'+ fId;},

                getFilesITD(item) {
                    this.items = [];
                    let fileType = this.valueITDData.value;
                    for (let i = 0; i < item.fileList.length; i++) {
                      let fileItem = {};
                      if (item.fileList[i].fileType === fileType) {
                      fileItem.id = item.fileList[i].id;
                      fileItem.src = window.location.href+ `files\\${item.fileList[i].id}`;
                      this.items.push(fileItem);
                      };
                   };
                },

                 getFormattedDate(dt) {
                  let moment = require('moment');
                  moment.locale('ru');
                  let resultDate = '';
                  resultDate = moment(dt, 'DD-MM-YYYY HH:mm:ss');
                  return resultDate.format('DD MMMM YYYY');
                  },


                        roundDouble(num) {
                            return Math.round(num * 100) / 100;
                        },

                 getFilesPhoto(item) {
                                    this.items = [];
                                    let fileType = this.valuePhotoData.value;
                                    for (let i = 0; i < item.fileList.length; i++) {
                                      let fileItem = {};
                                      if (item.fileList[i].fileType === fileType) {
                                      fileItem.id = item.fileList[i].id;
                                     fileItem.src = window.location.href+ `files\\${item.fileList[i].id}`;
                                      this.items.push(fileItem);
                                      };
                                    };
                                },



                deleteFilePhoto(fId) {
                            removeFilesApi.delete(fId).then(result => {
                            if (result.ok) {
                            let arr = findIndexByFileId(this.bypassRows, fId);
                            this.bypassRows[arr.rowIndex].fileList.splice(arr.fileIndex, 1);
                            this.getFilesPhoto(this.bypassRows[arr.rowIndex]);
                            }
                            })
                      },
               deleteFileITD(fId) {
                             removeFilesApi.delete(fId).then(result => {
                             if (result.ok) {
                             let arr = findIndexByFileId(this.bypassRows, fId);
                             this.bypassRows[arr.rowIndex].fileList.splice(arr.fileIndex, 1);
                             this.getFilesITD(this.bypassRows[arr.rowIndex]);
                                                 }
                                                 })
                                           },


                uploadFilesPhoto(item) {
                            if (this.files) {
                                     let formData = new FormData();
                                     for (let file of this.files) {formData.append("files", file, file.name);}
                                     this.$resource('/files/obj-upload-multiple{/objId}').save({objId: item.objectId, fileType: this.valuePhotoData.value}, formData).then(result =>
                                     {if (result.ok) {
                                                result.json().then(data => {
                                                const indexTable = this.bypassRows.indexOf(item);
                                                for (let i = 0; i < data.length; i++) {
                                                let arr = getFileInfo(data[i]);
                                                this.bypassRows[indexTable].fileList.push(arr);
                                                this.getFilesPhoto(this.bypassRows[indexTable]);
                                                   }
                                                }
                                                )
                                                }
                                                }
                                                );
                                     this.files = [];
                 }
                 },

                 uploadFilesITD(item) {
                                             if (this.files) {
                                                      let formData = new FormData();
                                                      for (let file of this.files) {formData.append("files", file, file.name);}
                                                      this.$resource('/files/obj-upload-multiple{/objId}').save({objId: item.objectId, fileType: this.valueITDData.value}, formData).then(result =>
                                                      {if (result.ok) {
                                                                 result.json().then(data => {
                                                                 const indexTable = this.bypassRows.indexOf(item);
                                                                 for (let i = 0; i < data.length; i++) {
                                                                 let arr = getFileInfo(data[i]);
                                                                 this.bypassRows[indexTable].fileList.push(arr);
                                                                 this.getFilesITD(this.bypassRows[indexTable]);
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
                                                         },
                 watch: {
                expanded: function() {
                 this.panels = false
                 }
        }
        }
</script>

<style>
<style>

