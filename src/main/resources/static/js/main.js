var bypassApi = Vue.resource('/bypass/{/id}');

Vue.component('bypass-row', {
    props: ['bypass'],
    template: '<tr>'+
                    '<td>{{ bypass.id }}</td>'+
                    '<td>{{ bypass.executor }}</td>'+
                    '<td>{{ bypass.address }}</td>'+
                    '<td>{{ bypass.dog_type }}</td>'+
                    '<td>{{ bypass.bypass_date }}</td>'+
                    '<td>{{ bypass.done_type }}</td>'+
               '</tr>'
});

Vue.component('bypasses-list', {
  props: ['bypasses'],
  template: '<table>'+
                    '<th>ID</th>'+
                    '<th>Исполнитель</th>'+
                    '<th>Адрес</th>'+
                    '<th>Тип договора</th>'+
                    '<th>Дата обхода</th>'+
                    '<th>Выполнение</th>'+
                    '<bypass-row v-for="bypass in bypasses" :key="bypass.id" :bypass="bypass" />'+
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