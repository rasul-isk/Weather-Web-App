<template>
  <div v-if="this.info.length">

    <div ref="main" class="weather-app" :class="this.backgroundImage">
      <div class="container">
        <h3 class="brand">Weather in Estonia</h3>
        <div style="padding: 50px">
          <h1 class="temp">{{ this.info[0].get(this.dayTime).get('tempmax') }}°</h1>
          <div class="city-time">
            <h1 class="name">{{ this.cityInfo.get('name') }}</h1>
            <small>
              <span class="time"> {{ this.clock }} <p> </p></span>
              <span class="date"> {{ this.today }}</span>
            </small>
          </div>

          <div class="weather">
            <!--               <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24"><path fill="none" d="M0 0h24v24H0z"/><path d="M12 18a6 6 0 1 1 0-12 6 6 0 0 1 0 12zM11 1h2v3h-2V1zm0 19h2v3h-2v-3zM3.515 4.929l1.414-1.414L7.05 5.636 5.636 7.05 3.515 4.93zM16.95 18.364l1.414-1.414 2.121 2.121-1.414 1.414-2.121-2.121zm2.121-14.85l1.414 1.415-2.121 2.121-1.414-1.414 2.121-2.121zM5.636 16.95l1.414 1.414-2.121 2.121-1.414-1.414 2.121-2.121zM23 11v2h-3v-2h3zM4 11v2H1v-2h3z"/></svg>-->
            <span class="condition">☀️{{ this.info[0].get(this.dayTime).get('text') }}</span>
          </div>
        </div>
      </div>

      <div class="panel">
        <form id="locationInput">
          <input type="text" class="search" placeholder="Search Location..."/>
          <button type="submit" class="submit">
            S
          </button>

          <!--          <section class="dropdown-wrapper">
                      <div @click="selectCityVisible = !selectCityVisible" class="selected-item">
                        <span v-if="selectedItem"> {{ selectedItem }}</span>
                        &lt;!&ndash; selectedItem.name &ndash;&gt;
                        <span v-else>Select City</span>
                        &lt;!&ndash; selectedItem.name &ndash;&gt;
                        <svg :class="selectCityVisible ? 'dropdown' : ''" class="drop-down-icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24">
                          <path fill="none" d="M0 0h24v24H0z" />
                          <path d="M12 10.828l-4.95 4.95-1.414-1.414L12 8l6.364 6.364-1.414 1.414z" />
                        </svg>
                      </div>

                      <div :class="selectCityVisible ? 'visible' : 'invisible'" class="dropdown-popover">
                        <input v-model="searchQuery" type="text" placeholder="Pick for city" />
                        <span v-if="filteredUser.length === 0">No data found!</span>
                        <div class="options">
                          <ul>
                            <li @click="selectItem(city)" v-for="city in filteredUser" v-bind:key="city.id">{{ city }}</li>
                          </ul>
                        </div>
                      </div>
                    </section>-->
        </form>
        <ul class="cities">
          <li class="city" @click="selectItem(city)" v-for="city in filteredUser" v-bind:key="city.id">{{ city }}</li>
        </ul>
        <ul class="details">
          <h4>Weather Details</h4>
          <li>
            <span> {{ this.cityInfo.get('phenomenon') }}</span>
            <span class="cloud">89% CHANGE</span>
          </li>
          <li>
            <span>Humidity</span>
            <span class="humidity">64% CHANGE</span>
          </li>
          <li>
            <span>Wind</span>
            <span class="wind">8 k/m</span>
          </li>
        </ul>
      </div>


    </div>
    <!--    <div id="app" class="min-vh-100 bg-light" style="padding-top: 60px">
          <h1 style="color: #15D6A0;">Weather in Estonia</h1>
          <hr class="center-line">
        </div>

        <div class="container" style="width:55%;">
          <h2>Overall Information</h2>
          <h4>From {{ this.info[0].get('night').get('tempmin') }}° to {{ this.info[0].get('night').get('tempmax') }}°</h4>
          <h5> Time: Night</h5>
          <h5> Date: {{ this.info[0].get('date') }}</h5>
          <p style="width:70%;margin-right: auto; margin-left: auto;"> {{ this.info[0].get('night').get('text') }}</p>

          <div v-if="!moreInformation" @click="moreInformation = !moreInformation;" class="details-button">More information</div>

          <div v-if="moreInformation">
            <p v-if="this.info[0].get('night').get('peipsi') != ''"> Peipsi: {{ this.info[0].get('night').get('peipsi') }}</p>
            <p v-if="this.info[0].get('night').get('sea') != ''"> {{ this.info[0].get('night').get('sea') }}</p>
           </div>
          <div v-if="moreInformation" @click="moreInformation = !moreInformation;" class="details-button">Less information</div>
        </div>

        <section class="dropdown-wrapper">
          <h4>City Information</h4>
          <div @click="selectCityVisible = !selectCityVisible" class="selected-item">
            <span v-if="selectedItem"> {{ selectedItem }}</span>  &lt;!&ndash; selectedItem.name &ndash;&gt;
            <span v-else>Select City</span>  &lt;!&ndash; selectedItem.name &ndash;&gt;
            <svg :class="selectCityVisible ? 'dropdown' : ''"
                 class="drop-down-icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24">
              <path fill="none" d="M0 0h24v24H0z"/>
              <path d="M12 10.828l-4.95 4.95-1.414-1.414L12 8l6.364 6.364-1.414 1.414z"/>
            </svg>
          </div>

          <div :class="selectCityVisible ? 'visible' : 'invisible'" class="dropdown-popover">
            <input v-model="searchQuery" type="text" placeholder="Pick for city"/>
            <span v-if="filteredUser.length ===0">No data found!</span>
            <div class="options">
              <ul>
                <li @click="selectItem(city)" v-for="city in filteredUser" v-bind:key="city.id"> {{ city }}</li>
              </ul>
            </div>
          </div>

        </section>

        <div class="container" style="width:55%;">
          <div v-if="selectedItem" class="container" style="width:55%">
            <p>Name: {{ this.cityInfo.get('name') }}</p>
            <p>Phenomenon: {{ this.cityInfo.get('phenomenon')}}</p>
            <p>Temp Min: {{ this.cityInfo.get('tempmin') }}</p>
            &lt;!&ndash;MIN BUT IN DAY TIME MAX &ndash;&gt;
          </div>

          </div>-->
    <!--    <section>
            <div class="switch">
        &lt;!&ndash;      DO YOU REMEMBER YOUTUBE VIDEO OF JS PROJECTS? USE IT TO DO SWITCH&ndash;&gt;
              <div class="button rounded-5 shadow p-4">
                <div class="text-uppercase">Daytime</div>
              </div>

              <div class="button rounded-5 shadow p-4" >
                <div class="text-uppercase">Night</div>
              </div>
            </div>
        </section>-->
    <!--        <table class="table table-striped">
              <thead>
              <th> Datetime</th>
              <th> Message</th>
              <th> City</th>
              <th> Condition</th>
              <th> Max temp</th> &lt;!&ndash; (if exist) &ndash;&gt;
              <th> Min temp</th>  &lt;!&ndash; (if exist) &ndash;&gt;
              <th> Wind Direction</th> &lt;!&ndash; (if exist) &ndash;&gt;
              <th> Min speed</th> &lt;!&ndash; (if exist) &ndash;&gt;
              <th> Max speed</th> &lt;!&ndash; (if exist) &ndash;&gt;
              </thead>
              <tbody>
              <tr v-for="weather in weatherInfo" v-bind:key="weather.id">
                <td> {{ weather.id }}</td>
                <td> {{ weather.dayTime }}</td>
                <td> {{ weather.message }}</td>
                <td> {{ weather.city }}</td>
                <td> {{ weather.condition }}</td>

              </tr>

              </tbody>
            </table>-->
  </div>

</template>

<script>

export default {
  name: 'WeatherInformation',
  data() {
    return {
      searchQuery: '',
      selectedItem: "Tartu",
      cityInfo: null,
      selectCityVisible: false,
      moreInformation: false,
      backgroundImage: 'bg-cloudy',
      info: [],
      cityArray: [],
      city: "Tartu",
      dayTime: "",
      clock: "",
      today: new Date()
    }
  },
  computed: {
    filteredUser() {
      const query = this.searchQuery.toLowerCase()
      if (this.searchQuery === "") {
        return this.cityArray;
      } else {
        return this.cityArray.filter((city) => {

          return city.toLowerCase().includes(query);
        })
      }
    },
  },
  mounted() {

  },
  methods: {
    selectItem(city) {
      // this.$refs.main.style.opacity = '0';
      this.selectedItem = city;
      this.cityInfo = this.info[0].get(this.dayTime).get('places').filter((el) => {
        return el.get('name') === city
      })[0];
      console.log(typeof this.info[0].get(this.dayTime).get('text').toLowerCase());
      this.$refs.main.style.opacity = '0';
      if (this.cityInfo.get('phenomenon').toLowerCase().includes("cloudy")) {
        this.backgroundImage = "bg-cloudy";
      }

      if (this.cityInfo.get('phenomenon').toLowerCase().includes("rain") ||
          this.cityInfo.get('phenomenon').toLowerCase().includes("shower")) {

        this.backgroundImage = "bg-rain";
      }

      if (this.cityInfo.get('phenomenon').toLowerCase().includes("sunny")) {
        this.backgroundImage = "bg-sunny";
      }

      if (this.cityInfo.get('phenomenon').toLowerCase().includes("snow")) {
        this.backgroundImage = "bg-snow";
      }
      this.$refs.main.style.opacity = '1';
      this.selectCityVisible = false;
      // console.log(this.cityInfo);
    },
    getWeather() {
      const weatherAPI = 'http://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng';
      const proxyURL = `https://thingproxy.freeboard.io/fetch/${weatherAPI}`

      fetch(proxyURL).then(response => response.text())
          .then(data => {
            let parser = new DOMParser();
            let xml = parser.parseFromString(data, "text/xml");
            let forecast = xml.getElementsByTagName('forecast');
            this.info = [];
            var dd = String(parseInt(String(this.today.getDate()).padStart(2, '0')) + 1);
            var mm = String(this.today.getMonth() + 1).padStart(2, '0'); //January is 0!
            var yyyy = this.today.getFullYear();
            this.clock = this.today.getHours() + ":" + this.today.getMinutes() + ":" + this.today.getSeconds();
            this.dayTime = this.today.getHours() > 20 || this.today.getHours() < 6 ? "night" : "day";

            this.today = yyyy + '-' + mm + '-' + dd;

            for (let itr = 0; itr < forecast.length; itr++) {
              let date = forecast[itr].getAttribute("date");

              let singleDay = new Map();
              let night = new Map();
              let day = new Map();

              let nightObj = forecast[itr].getElementsByTagName('night');
              let dayTimeObj = forecast[itr].getElementsByTagName('day');

              let phenomenon = nightObj[0].getElementsByTagName("phenomenon")[0].innerHTML;
              let generalMin = nightObj[0].getElementsByTagName("tempmin")[0].innerHTML;
              let generalMax = nightObj[0].getElementsByTagName("tempmax")[0].innerHTML;
              let generalInfo = nightObj[0].getElementsByTagName("text")[0].innerHTML;
              let sea = nightObj[0].getElementsByTagName("sea")[0]?.innerHTML;
              let peipsi = nightObj[0].getElementsByTagName("peipsi")[0]?.innerHTML;
              sea = sea ? sea : "";
              peipsi = peipsi ? peipsi : "";

              night.set('phenomenon', phenomenon);
              night.set('tempmin', generalMin);
              night.set('tempmax', generalMax);
              night.set('text', generalInfo);
              night.set('sea', sea);
              night.set('peipsi', peipsi);

              phenomenon = dayTimeObj[0].getElementsByTagName("phenomenon")[0].innerHTML;
              generalMin = dayTimeObj[0].getElementsByTagName("tempmin")[0].innerHTML;
              generalMax = dayTimeObj[0].getElementsByTagName("tempmax")[0].innerHTML;
              generalInfo = dayTimeObj[0].getElementsByTagName("text")[0].innerHTML;
              sea = dayTimeObj[0].getElementsByTagName("sea")[0]?.innerHTML;
              peipsi = dayTimeObj[0].getElementsByTagName("peipsi")[0]?.innerHTML;
              sea = sea ? sea : "";
              peipsi = peipsi ? peipsi : "";
              console.log(this.today);
              if (date === this.today) {

                let dayWindsArr = [];
                let dayPlacesArr = [];
                let nightWindsArr = [];
                let nightPlacesArr = [];

                let dayPlaces = dayTimeObj[0].getElementsByTagName('place');
                let dayWind = dayTimeObj[0].getElementsByTagName('wind');

                let nightPlaces = nightObj[0].getElementsByTagName('place');
                let nightWind = nightObj[0].getElementsByTagName('wind');

                for (let itr = 0; itr < dayPlaces.length; itr++) {
                  let place = new Map();
                  place.set('name', dayPlaces[itr].getElementsByTagName("name")[0].innerHTML);
                  place.set('phenomenon', dayPlaces[itr].getElementsByTagName("phenomenon")[0].innerHTML);
                  place.set('tempmax', dayPlaces[itr].getElementsByTagName("tempmax")[0].innerHTML);

                  dayPlacesArr.push(place);
                }

                for (let itr = 0; itr < dayWind.length; itr++) {
                  let wind = new Map();
                  wind.set('name', dayWind[itr].getElementsByTagName("name")[0].innerHTML);
                  wind.set('direction', dayWind[itr].getElementsByTagName("direction")[0].innerHTML);
                  wind.set('speedmin', dayWind[itr].getElementsByTagName("speedmin")[0].innerHTML);
                  wind.set('speedmax', dayWind[itr].getElementsByTagName("speedmax")[0].innerHTML);

                  dayWindsArr.push(wind);
                }

                for (let itr = 0; itr < nightPlaces.length; itr++) {
                  let place = new Map();
                  place.set('name', nightPlaces[itr].getElementsByTagName("name")[0].innerHTML);
                  place.set('phenomenon', nightPlaces[itr].getElementsByTagName("phenomenon")[0].innerHTML);
                  place.set('tempmin', nightPlaces[itr].getElementsByTagName("tempmin")[0].innerHTML);

                  nightPlacesArr.push(place);
                }

                for (let itr = 0; itr < nightWind.length; itr++) {
                  let wind = new Map();
                  wind.set('name', nightWind[itr].getElementsByTagName("name")[0].innerHTML);
                  wind.set('direction', nightWind[itr].getElementsByTagName("direction")[0].innerHTML);
                  wind.set('speedmin', nightWind[itr].getElementsByTagName("speedmin")[0].innerHTML);
                  wind.set('speedmax', nightWind[itr].getElementsByTagName("speedmax")[0].innerHTML);

                  nightWindsArr.push(wind);
                }

                day.set("places", dayPlacesArr);
                day.set("winds", dayWindsArr);
                night.set("winds", nightWindsArr);
                night.set("places", nightPlacesArr);
              }

              day.set('phenomenon', phenomenon);
              day.set('tempmin', generalMin);
              day.set('tempmax', generalMax);
              day.set('text', generalInfo);
              day.set('sea', sea);
              day.set('peipsi', peipsi);

              singleDay.set('date', date);
              singleDay.set('night', night);
              singleDay.set('day', day);

              this.info.push(singleDay);
            }
            console.log(this.info);

            this.cityArray = this.info[0].get('night').get('places').map((el) => {
              return el.get('name')
            });
            this.cityInfo = this.info[0].get('night').get('places').filter((el) => {
              return el.get('name') === this.city
            })[0];
            console.log(this.cityArray);
            console.log(this.cityInfo);
          });

    }
  },
  created() {
    this.getWeather()
  }
}
</script>


<style scoped lang="scss">
body {
  margin: 0;
  font-family: 'Rubik', sans-serif;
  background: #111;
}

* {
  box-sizing: border-box;
}

h1, h3 {
  font-weight: 400;
}

h3 {
  margin: 4rem;
}

.weather-app {
  overflow-y: hidden;
  min-height: 100vh;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  color: #fff;
  position: relative;
  transition: 500ms;
  opacity: 1;
}

.bg-cloudy {
  background-image: url(./images/day/cloudy.jpg);
}

.bg-sunny {
  background-image: url(./images/day/sunny.jpg);
}

.bg-rain {
  background-image: url(./images/day/rain.jpg);
}

.bg-snow {
  background-image: url(./images/day/snow.jpg);
}


.weather-app::before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.3);
  z-index: 0;
}

.container {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  flex-direction: column;
  padding: 2em 3em 4em 3em;


  margin-right: 0;
  padding: 0;
  margin-left: 0;
}

.container > div {
  display: flex;
  justify-content: center;
  align-items: center;
}

.city-time,
.temp,
.weather {
  margin: 0 1em;
}

.city-time h1 {
  margin: 0;
  margin-bottom: 0.2em;
  font-size: 3em;
}

.temp {
  font-size: 7em;
  margin: 0;
}

.weather img {
  display: block;
  margin: 0.5em 0;
}

.panel {
  position: absolute;
  width: 40%;
  height: 100%;
  top: 0;
  right: 0;
  background: rgba(110, 110, 110, 0.25);
  box-shadow: 0 8x 32px 0 rgba(0, 0, 0, 0.3);
  backdrop-filter: blur(10px);
  -webkit-backdrop-filter: blur(10px);
  border: 1px solid rgba(255, 255, 255, 0.18);
  z-index: 1;
  padding: 3em 2em;
  overflow-Y: scroll;
}

.panel form {
  margin-bottom: 3em;
}

.submit {
  position: absolute;
  top: 0;
  right: 0;
  padding: 1.5em;
  margin: 0;
  border: none;
  outline: none;
  background: #fa6d1b;
  color: #fff;
  cursor: pointer;
  font-size: 1.2em;
  transition: 0.4s;
}

.submit:hover {
  background: #fff !important;
  color: #000;
}

.search {
  background: none;
  border: none;
  border-bottom: 1px #ccc solid;
  padding: 0 1em 0.5em 0;
  width: 80%;
  color: #fff;
  font-size: 1.1em;
}

.search:focus {
  outline: none;
}

.search:placeholder {
  color: #ccc;
}

.panel ul {
  padding: 0 0 1em 0;
  margin: 2em 0;
  border-bottom: 1px #ccc solid;
}

.panel ul li {
  color: #ccc;
  margin: 2.5em 0;
}

.panel ul h4 {
  margin: 3em 0;
}

.city {
  display: block;
  cursor: pointer;
}

.city:hover {
  color: #fff;
}

.details li {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

@media screen and (max-width: 800px) {
  .panel, .container {
    position: relative;
    width: 100%;
    top: initial;
  }
}

@media screen and (max-width: 500px) {
  html {
    font-size: 12px;
  }
}

@media screen and (max-width: 300px) {
  .weather-app {
    min-height: 40em;
  }
}


.dropdown-wrapper {
  max-width: 12rem;
  position: relative;
  margin: 0 auto;


  .selected-item {
    height: 2rem;
    border: 2px solid lightgray;
    border-radius: 4px;
    padding: 5px 10px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 18px;
    font-weight: 400;

    .drop-down-icon {
      transform: rotate(0deg);
      transition: all 0.5s ease;

      &.dropdown {
        transform: rotate(180deg);
      }
    }
  }

  .dropdown-popover {
    position: absolute;
    border: 2px solid lightgray;
    top: 46px;
    left: 0;
    right: 0;
    max-width: 100%;
    padding: 10px;

    visibility: hidden;
    max-height: 0px;
    overflow: hidden;

    &.visible {
      visibility: visible;
      max-height: 450px;
      display: contents;
    }

    input {
      width: 90%;
      border: 2px solid lightgray;
      font-size: 16px;
      padding-left: 8px;
    }

    .options {
      width: 100%;

      ul {
        list-style: none;
        text-align: left;
        padding-left: 8px;
        max-height: 180px;
        overflow-y: scroll;
        overflow-x: hidden;
        border: 1px solid lightgray;

        li {
          width: 100%;
          border-bottom: 1px solid lightgray;
          padding: 10px;
          background-color: #f1f1f1;
          cursor: pointer;
          font-size: 16px;

          &:hover {
            background: #70878a;
            color: #fff
          }
        }
      }
    }
  }
}


/*DESIGN FROM APP VUE + OLD DESIGN
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #021510;
  margin-top: 0px;
}

.center-line {
  margin-left: auto;
  margin-right: auto;
  width: 60%;

}

.button {
  cursor: pointer;
  font-weight: bold;
  font-size: x-large;
  margin-right: 1rem;
  margin-bottom: 1rem;
  width: 12rem;
  background-color: #15d6A0;
  justify-content: center;
  margin-left: auto;
  margin-right: auto;
}
.details-button {
  cursor: pointer;
  font-weight: 300;
  font-style: italic;
  margin-right: 1rem;
  margin-bottom: 1rem;
  width: auto;
  justify-content: center;
  margin-left: auto;
  margin-right: auto;
  color: #FFC425;
}
.button:hover {
  background-color: yellow;
}

.switch {
  justify-content: center;
  display: inline-block;
  width: fit-content;
  background-color: #FFC425;
}

.button:hover {
  border-style: groove;
  border-color: black;

}*/
</style>
