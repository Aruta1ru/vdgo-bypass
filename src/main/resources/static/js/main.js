
function getIndex(list, id) {
    for (var i=0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }
    return -1;
}

var bypassApi = Vue.resource('/bypass{/id}');

Vue.component('bypass-row', {
    props: ['bypass', 'bypasses'],
    template: '<tr>'+
                    '<td style="padding: 5px 3px; text-align: center;  border: 2px solid black;">{{ bypass.id }}</td>'+
                    '<td style="padding: 5px 3px; text-align: left;  border: 2px solid black;">{{ bypass.executor.name }}</td>'+
                    '<td style="padding: 5px 3px; text-align: left;  border: 2px solid black;">{{ bypass.address.addr }}</td>'+
                    '<td style="padding: 5px 3px; text-align: center;  border: 2px solid black;">{{ bypass.dogType }}</td>'+
                    '<td style="padding: 5px 3px; text-align: center;  border: 2px solid black;">{{ bypass.bypassDate }}</td>'+
                    '<td style="padding: 5px 3px; text-align: center;  border: 2px solid black;">{{ bypass.doneType }}</td>'+
                    '<td style="padding: 5px 3px; text-align: left;"><input type="button" value="Выполнено" @click="edit" /></td>'+
               '</tr>',
     methods: {
           edit: function() {
                bypassApi.update({id: this.bypass.id}, {executor: this.bypass.executor,
                                address: this.bypass.address, dogType: this.bypass.dogType,
                                bypassDate: this.bypass.bypassDate, doneType: 1}).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.bypasses, data.id);
                        this.bypasses.splice(index, 1, data);
                    })
                );
           }
     }
});

Vue.component('bypasses-list', {
  props: ['bypasses'],
  data: function() {
        return {
                bypass: null
        }
  },
  template: '<table style="width: 80%;">'+
                    '<th style="border: 2px solid red;">ID</th>'+
                    '<th style="border: 2px solid red;">Исполнитель</th>'+
                    '<th style="border: 2px solid red;">Адрес</th>'+
                    '<th style="border: 2px solid red;">Тип договора</th>'+
                    '<th style="border: 2px solid red;">Дата обхода</th>'+
                    '<th style="border: 2px solid red;">Статус выполнения</th>'+
                    '<bypass-row v-for="bypass in bypasses" :key="bypass.id" :bypass="bypass" :bypasses="bypasses" />'+
            '</table>',
});

var app = new Vue({
  el: '#app',
  template:
        '<div>' +
                '<div v-if="!profile">Для работы с приложением необходимо <a href="/login">авторизоваться</a></div>' +
                '<div v-else>' +
                    '<div>{{profile.name}}&nbsp;<a href="/logout">Выйти</a></div>' +
                    '<bypasses-list :bypasses="bypasses" />' +
                '</div>' +
        '</div>',
  data: {
    bypasses: frontendData.bypasses,
    profile: frontendData.profile
  },
  created: function() {
  }
});