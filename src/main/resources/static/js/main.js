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
                    '<td style="padding: 5px 3px; text-align: left;  border: 2px solid black;">{{ bypass.executor }}</td>'+
                    '<td style="padding: 5px 3px; text-align: left;  border: 2px solid black;">{{ bypass.address }}</td>'+
                    '<td style="padding: 5px 3px; text-align: center;  border: 2px solid black;">{{ bypass.dog_type }}</td>'+
                    '<td style="padding: 5px 3px; text-align: center;  border: 2px solid black;">{{ bypass.bypass_date }}</td>'+
                    '<td style="padding: 5px 3px; text-align: center;  border: 2px solid black;">{{ bypass.done_type }}</td>'+
                    '<td style="padding: 5px 3px; text-align: left;"><input type="button" value="Выполнено" @click="edit" /></td>'+
               '</tr>',
     methods: {
           edit: function() {
                bypassApi.update({id: this.bypass.id}, {done_type: "выполнено"}).then(result =>
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
                    '<th style="border: 2px solid red;">Выполнение</th>'+
                    '<bypass-row v-for="bypass in bypasses" :key="bypass.id" :bypass="bypass" :bypasses="bypasses" />'+
            '</table>',
  created: function() {
        bypassApi.get().then(result =>
            result.json().then(data =>
                data.forEach(bypass => this.bypasses.push(bypass))
            )
        )
  }
});

var app = new Vue({
  el: '#app',
  template: '<bypasses-list :bypasses="bypasses" />',
  data: {
    bypasses: [

    ]
  }
});