<template>
  <div class="container">
    <h1 class="text-center">Weather Information</h1>
    <table class="table table-striped">
      <thead>
      <th> Datetime</th>
      <th> Message</th>
      <th> City</th>
      <th> Condition</th>
      <th> Max temp</th> <!-- (if exist) -->
      <th> Min temp</th>  <!-- (if exist) -->
      <th> Wind Direction</th> <!-- (if exist) -->
      <th> Min speed</th> <!-- (if exist) -->
      <th> Max speed</th> <!-- (if exist) -->
      </thead>
      <tbody>
      <tr v-for="weather in weatherInfo" v-bind:key="weather.id">
        <td> {{ weather.id }}</td>
        <td> {{ weather.dateTime }}</td>
        <td> {{ weather.message }}</td>
        <td> {{ weather.city }}</td>
        <td> {{ weather.condition }}</td>

      </tr>

      </tbody>
    </table>
  </div>
</template>

<script>

// import WeatherService from "@/services/WeatherService";
// import axios from "axios";
export default {
  name: 'WeatherInformation',
  data() {
    return {
      weatherInfo: []
    }
  },
  methods: {
    getWeather() {
      const weatherAPIurl = 'http://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng';
      const proxy = `https://thingproxy.freeboard.io/fetch/${weatherAPIurl}`

      fetch(proxy).then(response => response.text())
          // .then ((response) => console.log(response));
          .then(data => {
            //console.log(data);  //string
            let parser = new DOMParser();
            let xml = parser.parseFromString(data, "application/xml");
            // document.getElementById('output').textContent = data;
            console.log(xml);
          });

    }
  },
  created() {
    this.getWeather()
  }
}
</script>
