<template>
  <div v-if="this.info.length">
    <div ref="main" class="weather-app" :class="this.backgroundImage">
      <div class="container">


        <!--        <h3 class="brand">Weather in Estonia</h3>-->

        <div class="switch-button" style="margin-left: auto;">
          <div @click="this.dayTime='day', selectItem(city)" class="day-button"
               :class="this.dayTime=='day' ? 'selected' : 'unSelected'">Day
          </div>
          <div @click="this.dayTime='night', selectItem(city)" class="night-button"
               :class="this.dayTime=='night' ? 'selected' : 'unSelected'">Night
          </div>
        </div>


        <div class="time" style="padding-left: 2.7em; margin-top: auto;">
          <h1 class="temp">{{ this.clock }}</h1>
        </div>
        <div class="time" style="padding-left: 2.7em">
          <h1 style="font-size: 6em">{{ this.today }}</h1>
        </div>

        <div style="padding-bottom: 1em; padding-right: 1em; padding-left: 2.15em">
          <!--          <h1 style="font-size: 8em">{{ this.info[0].get(this.dayTime).get('tempmax') }}°</h1>-->
          <!--          <h1 v-if = "this.dayTime = 'night'" style="font-size: 8em">{{ this.info[0].get(this.dayTime).get('places').get(this.city).get('tempmin') }}°</h1>-->
          <!--          <h1 v-if = "this.dayTime = 'day'" style="font-size: 8em">{{ this.info[0].get(this.dayTime).get('places').get(this.city).get('tempmax') }}°</h1>-->
          <h1 v-if="this.dayTime == 'day'" style="font-size: 8em">{{ this.cityInfo.get('tempmax') }}°</h1>
          <h1 v-else style="font-size: 8em">{{ this.cityInfo.get('tempmin') }}°</h1>
          <div class="city-time">
            <h1 style="font-size: 5em">{{ this.cityInfo.get('name') }}</h1>
          </div>

          <div class="weather">
            <img v-if="this.backgroundImage === 'bg-sunny'" src="./images/icons/sunIco.png" alt="sun Icon"/>
            <img v-if="this.backgroundImage === 'bg-cloudy'" src="./images/icons/cloudIco.png" alt="cloud Icon"/>
            <img v-if="this.backgroundImage === 'bg-rain'" src="./images/icons/rainIco.png" alt="rain Icon"/>
            <img v-if="this.backgroundImage === 'bg-snow'" src="./images/icons/snowIco.png" alt="snow Icon"/>
          </div>

          <div class="weather">
            <h2 class="condition" style="font-size: 3em;">{{ this.cityInfo.get('phenomenon') }}</h2>
          </div>
        </div>
      </div>

      <div class="panel">
        <form id="locationInput">
          <section class="dropdown-wrapper">
            <div @click="selectCityVisible = !selectCityVisible" class="selected-item">
              <span v-if="selectedCity"> {{ selectedCity }}</span>

              <span v-else>Select City</span>

              <svg :class="selectCityVisible ? 'dropdown' : ''" class="drop-down-icon"
                   xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24">
                <path fill="none" d="M0 0h24v24H0z"/>
                <path d="M12 13.172l4.95-4.95 1.414 1.414L12 16 5.636 9.636 7.05 8.222z" fill="rgba(255,255,255,1)"/>
              </svg>
            </div>

            <div :class="selectCityVisible ? 'visible' : 'invisible'" class="dropdown-popover">
              <input v-model="searchQuery" type="text" placeholder="Type here..."/>
              <span v-if="filteredUser.length === 0">No data found!</span>
              <div class="options">
                <ul class="cities">
                  <li @click="selectItem(city)" v-for="city in filteredUser" v-bind:key="city.id">{{ city }}</li>
                </ul>
              </div>
            </div>
          </section>
        </form>

        <ul class="details">
          <div @click="this.detailsVisible = !this.detailsVisible" class="div-section">
            <h4>Weather Details
            </h4>
            <svg :class="this.detailsVisible ? 'dropdown' : ''" class="drop-down-icon"
                 xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="40" height="40">
              <path fill="none" d="M0 0h24v24H0z"/>
              <path d="M12 13.172l4.95-4.95 1.414 1.414L12 16 5.636 9.636 7.05 8.222z" fill="rgba(255,255,255,1)"/>
            </svg>
          </div>
          <div v-if="detailsVisible">
            <li>
            <span class="cloud"> <h5 style="font-style: italic">General Information</h5>
              {{
                this.info[0].get(this.dayTime).get('text')
              }}</span>
            </li>
            <!--
                      <li>
                        <span v-if="this.dayTime == 'day'">Maximum Temperature</span>
                        <span v-if="this.dayTime == 'night'">Minimum Temperature</span>

                        <span v-if="this.dayTime == 'day'" class="humidity">{{ this.cityInfo.get('tempmax') }}°</span>
                        <span v-if="this.dayTime == 'night'" class="humidity">{{ this.cityInfo.get('tempmin') }}°</span>
                      </li>
            -->

            <li v-if="this.info[0].get(this.dayTime).get('peipsi') != ''">
            <span class="cloud"> <h5 style="font-style: italic">Peipsi</h5>
              {{
                this.info[0].get(this.dayTime).get('peipsi')
              }}</span>
            </li>

            <li v-if="this.info[0].get(this.dayTime).get('sea') != ''">
            <span class="cloud"> <h5 style="font-style: italic">Sea</h5>
              {{
                this.info[0].get(this.dayTime).get('sea')
              }}</span>
            </li>


          </div>
        </ul>

        <ul class="details">
          <div @click="this.windVisible = !this.windVisible" class="div-section">
            <h4>Wind Details
            </h4>
            <svg :class="this.windVisible ? 'dropdown' : ''" class="drop-down-icon"
                 xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="40" height="40">
              <path fill="none" d="M0 0h24v24H0z"/>
              <path d="M12 13.172l4.95-4.95 1.414 1.414L12 16 5.636 9.636 7.05 8.222z" fill="rgba(255,255,255,1)"/>
            </svg>
          </div>
          <div v-if="windVisible">
            <hr>
            <div style="display: flex;">
              <span style="width: 33.3%; text-align: left;">Location</span>
              <span style="width: 33.3%; text-align: center;">Direction</span>
              <span style="width: 33.3%; text-align: right;">Speed</span>
            </div>
            <hr style="border-top:dashed;">
            <!--                        <li>
                                      <span style="width: 33.3%; text-align: left">Location</span>
                                      <span style="width: 33.3%; text-align: center">Direction</span>
                                      <span style="width: 33.3%; text-align: right">Speed</span>
                                    </li>-->
            <li class="wind" v-for="wind in this.info[0].get(this.dayTime).get('winds')" v-bind:key="wind.id">
              <span style="width: 33.3%; text-align: left">{{ wind.get('name') }}</span>
              <span style="width: 33.3%; text-align: center">{{ wind.get('direction') }} </span>
              <span style="width: 33.3%; text-align: right">{{ wind.get('speedmin') }} to {{
                  wind.get('speedmax')
                }} m/s</span>
            </li>
          </div>
        </ul>


        <ul class="details">
          <div @click="this.futureWeather = !this.futureWeather" class="div-section">
            <h4>Weather in next 3 days</h4>
            <svg :class="this.futureWeather ? 'dropdown' : ''" class="drop-down-icon"
                 xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="40" height="40">
              <path fill="none" d="M0 0h24v24H0z"/>
              <path d="M12 13.172l4.95-4.95 1.414 1.414L12 16 5.636 9.636 7.05 8.222z" fill="rgba(255,255,255,1)"/>
            </svg>
          </div>

          <div v-if="futureWeather">

            <hr>
            <li>
              <!--              style="padding: 0px 1em"-->
              <div @click="this.selectedWeatherBtn = [1,0,0],this.selectedDateIndex=1" class="weather-button"
                   :class="selectedWeatherBtn[0] ? 'selected' : 'unSelected'">
                <h5 style="text-align: center">{{ this.info[1].get('date').slice(8, 10) }}
                  {{ months[parseInt(this.info[1].get('date').slice(5, 7)) - 1] }}
                  '{{ this.info[1].get('date').slice(2, 4) }}</h5>
              </div>

              <div @click="this.selectedWeatherBtn = [0,1,0],this.selectedDateIndex=2" class="weather-button"
                   :class="selectedWeatherBtn[1] ? 'selected' : 'unSelected'">
                <h5 style="text-align: center">{{ this.info[2].get('date').slice(8, 10) }}
                  {{ months[parseInt(this.info[2].get('date').slice(5, 7)) - 1] }} '{{
                    this.info[2].get('date').slice(2, 4)
                  }}</h5>
              </div>

              <div @click="this.selectedWeatherBtn = [0,0,1],this.selectedDateIndex=3" class="weather-button"
                   :class="selectedWeatherBtn[2] ? 'selected' : 'unSelected'">
                <h5 style="text-align: center">{{ this.info[3].get('date').slice(8, 10) }}
                  {{ months[parseInt(this.info[2].get('date').slice(5, 7)) - 1] }} '{{
                    this.info[2].get('date').slice(2, 4)
                  }}</h5>
              </div>
            </li>

            <hr style="border-top:dashed;">


            <li>

              <span>
                <h5 style="font-style: italic;">General Information</h5>
                {{ this.info[this.selectedDateIndex].get(this.dayTime).get('text') }}</span>
            </li>
            <hr style="border-top:dashed;">

            <li>
              <span style="width: 33.3%; text-align: left;">Phenomenon</span>
              <span style="width: 33.3%; text-align: center;">Daytime Temperature</span>
              <span style="width: 33.3%; text-align: right;">Nighttime Temperature</span>

            </li>
            <li class="wind">
              <span style="width: 33.3%; text-align: left;">{{
                  this.info[this.selectedDateIndex].get(this.dayTime).get('phenomenon')
                }}</span>
              <span style="width: 33.3%; text-align: center;">{{
                  this.info[this.selectedDateIndex].get('day').get('tempmin')
                }}° to {{ this.info[this.selectedDateIndex].get('day').get('tempmax') }}° </span>
              <span style="width: 33.3%; text-align: right;">{{
                  this.info[this.selectedDateIndex].get('night').get('tempmin')
                }}° to {{ this.info[this.selectedDateIndex].get('night').get('tempmax') }}° </span>
            </li>
            <!--
            <h5 style="text-align: center">{{ this.info[2].get('date').slice(8, 10) }}
              {{ months[parseInt(this.info[2].get('date').slice(5, 7)) - 1] }} '{{
                this.info[2].get('date').slice(2, 4)
              }}</h5>
            <hr style="border-top:dashed;">
            <li>
              <span>General Information</span>
              <span style="width: 65%;text-align: right;">{{ this.info[2].get(this.dayTime).get('text') }}</span>
            </li>
            <li>
              <span>Phenomenon</span>
              <span>{{ this.info[2].get(this.dayTime).get('phenomenon') }}</span>
            </li>
            <li class="wind">
              <span>Daytime Temperature</span>
              <span>{{ this.info[2].get('day').get('tempmin') }}° to {{ this.info[2].get('day').get('tempmax') }}° </span>
            </li>
            <li class="wind">
              <span>Nighttime Temperature</span>
              <span>{{ this.info[2].get('night').get('tempmin') }}° to {{
                  this.info[2].get('night').get('tempmax')
                }}° </span>
            </li>

            <hr>
            <h5 style="text-align: center">{{ this.info[3].get('date').slice(8, 10) }}
              {{ months[parseInt(this.info[3].get('date').slice(5, 7)) - 1] }} '{{
                this.info[3].get('date').slice(2, 4)
              }}</h5>
            <hr style="border-top:dashed;">
            <li>
              <span>General Information</span>
              <span style="width: 65%;text-align: right;">{{ this.info[3].get(this.dayTime).get('text') }}</span>
            </li>
            <li>
              <span>Phenomenon</span>
              <span>{{ this.info[3].get(this.dayTime).get('phenomenon') }}</span>
            </li>
            <li class="wind">
              <span>Daytime Temperature</span>
              <span>{{ this.info[3].get('day').get('tempmin') }}° to {{ this.info[3].get('day').get('tempmax') }}° </span>
            </li>
            <li class="wind">
              <span>Nighttime Temperature</span>
              <span>{{ this.info[3].get('night').get('tempmin') }}° to {{
                  this.info[3].get('night').get('tempmax')
                }}° </span>
            </li>-->

          </div>

        </ul>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'WeatherInformation',
  data() {
    return {
      searchQuery: '',
      selectedCity: "Tartu",
      selectedDateIndex: 1,
      selectedWeatherBtn: [1, 0, 0],
      selectedDaytime: "",
      cityInfo: null,
      selectCityVisible: false,
      moreInformation: false,
      backgroundImage: 'bg-cloudy',
      info: [],
      cityArray: [],
      city: "Tartu",
      dayTime: "",
      clock: "",
      today: new Date(),
      objectIndex: null,
      windVisible: false,
      detailsVisible: true,
      futureWeather: false,
      months: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
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
    getNow: function () {
      const today = new Date();
      const hours = today.getHours() < 10 ? "0" + today.getHours().toString() : today.getHours();
      const minutes = today.getMinutes() < 10 ? "0" + today.getMinutes().toString() : today.getMinutes();
      const seconds = today.getSeconds() < 10 ? "0" + today.getSeconds().toString() : today.getSeconds();
      const time = hours + ":" + minutes + ":" + seconds;
      this.clock = time;
    },
    selectItem(city) {
      // this.$refs.main.style.opacity = '0';
      this.city = city;
      this.selectedCity = city;
      this.cityInfo = this.info[0].get(this.dayTime).get('places').filter((el) => {
        return el.get('name') === city
      })[0];
      this.$refs.main.style.opacity = '0';

      if (this.cityInfo.get('phenomenon').toLowerCase().includes("cloud")) {
        this.backgroundImage = "bg-cloudy";
      }

      if (this.cityInfo.get('phenomenon').toLowerCase().includes("rain") ||
          this.cityInfo.get('phenomenon').toLowerCase().includes("shower")) {

        this.backgroundImage = "bg-rain";
        console.log("enters lol");
      }

      if (this.cityInfo.get('phenomenon').toLowerCase().includes("sun")) {
        this.backgroundImage = "bg-sunny";
      }

      if (this.cityInfo.get('phenomenon').toLowerCase().includes("snow")) {
        this.backgroundImage = "bg-snow";
      }
      this.$refs.main.style.opacity = '1';
      console.log(this.cityInfo);

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
            let dd = String(this.today.getDate()).padStart(2, '0');
            let dd1 = String(parseInt(String(this.today.getDate()).padStart(2, '0')) + 1);
            let mm = String(this.today.getMonth() + 1).padStart(2, '0'); //January is 0!
            let yyyy = this.today.getFullYear();
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
              if (date === this.today || date === String(yyyy + '-' + mm + '-' + dd1)) {

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

            this.cityArray = this.info[0].get(this.dayTime).get('places').map((el) => {
              return el.get('name')
            });
            this.cityInfo = this.info[0].get(this.dayTime).get('places').filter((el) => {
              return el.get('name') === this.city
            })[0];
            this.today = "Mon" + ", " + dd + " " + this.months[parseInt(mm) - 1] + " '" + yyyy.toString().slice(2);
            console.log(this.cityArray);
            console.log(this.cityInfo);
            this.selectedDate = this.info[0].get('date');
          });

    }
  },
  created() {
    this.getWeather()
    setInterval(this.getNow, 1000);
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

h4 {
  font-size: 2rem;
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
  background-image: url(images/weather-pics/cloudy.jpg);
}

.bg-sunny {
  background-image: url(images/weather-pics/sunny.jpg);
}

.bg-rain {
  background-image: url(images/weather-pics/rain.jpg);
}

.bg-snow {
  background-image: url(images/weather-pics/snow.jpg);
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

.switch-button {
  margin-right: 2em;
  margin-top: 2em;
  background: rgba(110, 110, 110, 0.25);
  box-shadow: 0 8x 32px 0 rgba(0, 0, 0, 0.3);
  backdrop-filter: blur(10px);
  -webkit-backdrop-filter: blur(10px);
  border: 2px solid rgba(255, 255, 255, 0.18);
  cursor: pointer;
  border-radius: 30px;
}

.day-button {
  padding: 1.4em 1.7em 1.4em;
  border-bottom-left-radius: 30px;
  border-top-left-radius: 30px;
  border-right: 1px solid rgba(255, 255, 255, 0.18);

  &.selected {
    background: rgba(255, 196, 37, 1);
    transition: 0.55s cubic-bezier(1, -0.25, 0.54, 1.08);
    color: #fff;
  }
}

.weather-button {
  background: rgba(110, 110, 110, 0.25);
  box-shadow: 0 8x 32px 0 rgba(0, 0, 0, 1);
  backdrop-filter: blur(10px);
  -webkit-backdrop-filter: blur(10px);
  border: 2px solid rgba(255, 255, 255, 0.18);
  cursor: pointer;
  border-radius: 30px;
  padding: 1.4em 2em;
  margin: 0;

  &.selected {
    background: rgba(255, 196, 37, 0.8);
    transition: 0.2s cubic-bezier(1, -0.25, 0.54, 1.08);
    color: #fff;
  }
}


.night-button {
  border-left: 1px solid rgba(255, 255, 255, 0.18);
  padding: 1.4em;
  border-bottom-right-radius: 30px;
  border-top-right-radius: 30px;

  &.selected {
    background: rgba(147, 104, 183, 1);
    transition: 0.55s cubic-bezier(1, -0.25, 0.54, 1.08);
    color: #fff;
  }
}

.unSelected {
  transition: 1s ease-in;
  background: rgba(110, 110, 110, 0.25);
  color: #fff;
}

.unSelected:hover {
  background: #28a4de;
  transition: 0.2s ease-in-out;
  color: #fff;
}

.div-section {
  display: flex;
  justify-content: space-between;
  cursor: pointer;
}

.container {
  position: absolute;
  top: 0;
  left: 0;
  width: 60%;
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
  font-size: 5em;
  margin: 0;
}

.weather img {
  max-width: 7rem;
  max-height: 7rem;
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
  //margin-bottom: 3em;
  padding-bottom: 2em;
  border-bottom: 2px #ccc solid;
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
  border-bottom: 2px #ccc solid;
}

.panel ul li {
  color: #fff;
  margin: 2em 0;
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

.drop-down-icon {
  align-self: center;
  transform: rotate(0deg);
  transition: all .6s ease-out;

  &.dropdown {
    transform: rotate(180deg);
  }
}

.dropdown-wrapper {
  max-width: 100%;
  position: relative;
  margin: 0 auto;

  .selected-item {
    height: auto;
    border: 2px solid lightgray;
    border-radius: 7px;
    padding: 10px;
    display: flex;
    justify-content: space-between;
    cursor: pointer;
    align-items: center;
    font-size: 18px;
    font-weight: 400;

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
      background: transparent;
      width: 100%;
      height: auto;
      margin-top: 1rem;
      border: 2px solid lightgray;
      font-size: 18px;
      border-radius: 7px;
      padding: 10px;
      color: #fff;
    }

    input::placeholder { /* Chrome, Firefox, Opera, Safari 10.1+ */
      opacity: 1; /* Firefox */
      color: #fff;
    }

    .options {
      width: 100%;

      ul {
        padding: 0 0 0em 0;
        list-style: none;
        text-align: left;
        max-height: 200px;
        overflow-y: scroll;
        overflow-x: hidden;
        border: 1px solid lightgray;

        li {
          margin: 0 !important;
          border-bottom: 1px solid lightgray;
          padding: 20px;
          cursor: pointer;
          font-size: 18px;

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
