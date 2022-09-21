<template>
  <div v-if="info.length!=0">


    <div class="container" style="width:55%;">
      <h2>Overall Information</h2>
      <h4>From {{ this.info[0].get('night').get('tempmin') }}° to {{ this.info[0].get('night').get('tempmax') }}°</h4>
      <h5> Date: {{ this.info[0].get('date') }}</h5>
      <p style="width:70%;margin-right: auto; margin-left: auto;"> {{ this.info[0].get('night').get('text') }}</p>

      <!--    CREATON BUTTON!!!!!-->
      <p v-if="this.info[0].get('night').get('peipsi') != ''"> Peipsi: {{ this.info[0].get('night').get('peipsi') }}</p>
      <p v-if="this.info[0].get('night').get('sea') != ''"> {{ this.info[0].get('night').get('sea') }}</p>

      <h4>City Information</h4>
    </div>

    <section class="dropdown-wrapper">

      <div @click="isVisible = !isVisible" class="selected-item">
        <span v-if="selectedItem"> {{ selectedItem }}</span>  <!-- selectedItem.name -->
        <span v-else>Select City</span>  <!-- selectedItem.name -->
        <svg :class="isVisible ? 'dropdown' : ''"
             class="drop-down-icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24">
          <path fill="none" d="M0 0h24v24H0z"/>
          <path d="M12 10.828l-4.95 4.95-1.414-1.414L12 8l6.364 6.364-1.414 1.414z"/>
        </svg>
      </div>

      <div :class="isVisible ? 'visible' : 'invisible'" class="dropdown-popover">
        <input v-model="searchQuery" type="text" placeholder="Pick for city"/>
        <span v-if="filteredUser.length ===0">No data found!</span>
        <div class="options">
          <ul>
            <!--          <li v-for="(user,index) in userArray" :key="`user-${index}`"> {{ user.name }}</li> FOR LISTING ELEMENTS FRom API REQUEST -->
            <!--          <li v-for="city in cityArray" v-bind:key ="city.id"> {{ city }}</li>-->
            <li @click="selectItem(city)" v-for="city in filteredUser" v-bind:key="city.id"> {{ city }}</li>
            <!--        HEREEEEEEEEEEEEEEEEEEEEEE-->
          </ul>
        </div>
      </div>
    </section>

    <div v-if="selectedItem!=''" class="container" style="width:55%">
      <h1>{{ this.info[0].get('night') }}</h1>
    </div>

    <!--<section>-->
    <!--    <div class="switch">-->
    <!--&lt;!&ndash;      DO YOU REMEMBER YOUTUBE VIDEO OF JS PROJECTS? USE IT TO DO SWITCH&ndash;&gt;-->
    <!--      <div class="button rounded-5 shadow p-4">-->
    <!--        <div class="text-uppercase">Daytime</div>-->
    <!--      </div>-->

    <!--      <div class="button rounded-5 shadow p-4" >-->
    <!--        <div class="text-uppercase">Night</div>-->
    <!--      </div>-->
    <!--    </div>-->
    <!--</section>-->

    <!--    <div class="menu-item">-->
    <!--      <a href="#">-->
    <!--        Tartu-->
    <!--      </a>-->
    <!--    </div>-->


    <!--      <label for="tartu">Tartu</label>-->
    <!--      <input type="radio" value="tartu" id="tartu" v-model="city" name="city">-->

    <!--      <label for="talinn">Talinn</label>-->
    <!--      <input type="radio" value="talinn" id="talinn" v-model="city" name="city">-->

    <!--      <label for="tartu">Tartu</label>-->
    <!--      <input type="radio" value="tartu1" id="tartu1" v-model="city" name="city">-->

    <!--      <label for="tartu">Tartu</label>-->
    <!--      <input type="radio" value="tartu2" id="tartu2" v-model="city" name="city">-->


    <!--    <table class="table table-striped">-->
    <!--      <thead>-->
    <!--      <th> Datetime</th>-->
    <!--      <th> Message</th>-->
    <!--      <th> City</th>-->
    <!--      <th> Condition</th>-->
    <!--      <th> Max temp</th> &lt;!&ndash; (if exist) &ndash;&gt;-->
    <!--      <th> Min temp</th>  &lt;!&ndash; (if exist) &ndash;&gt;-->
    <!--      <th> Wind Direction</th> &lt;!&ndash; (if exist) &ndash;&gt;-->
    <!--      <th> Min speed</th> &lt;!&ndash; (if exist) &ndash;&gt;-->
    <!--      <th> Max speed</th> &lt;!&ndash; (if exist) &ndash;&gt;-->
    <!--      </thead>-->
    <!--      <tbody>-->
    <!--      <tr v-for="weather in weatherInfo" v-bind:key="weather.id">-->
    <!--        <td> {{ weather.id }}</td>-->
    <!--        <td> {{ weather.dateTime }}</td>-->
    <!--        <td> {{ weather.message }}</td>-->
    <!--        <td> {{ weather.city }}</td>-->
    <!--        <td> {{ weather.condition }}</td>-->

    <!--      </tr>-->

    <!--      </tbody>-->
    <!--    </table>-->

    <!--  <h4>City : {{city}}</h4>-->
  </div>
</template>

<script>

export default {
  name: 'WeatherInformation',
  data() {
    return {
      searchQuery: '',
      selectedItem: null,
      isVisible: false,
      info: [],
      cityArray: [],
      city: null
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
          // WHEN U USE MAP -> return Object.values(city).some((word => String(word).toLowerCase().includes(query)));
        })
      }
    },
  },
  mounted() {

  },
  methods: {
    selectItem(city) {
      this.selectedItem = city;
      this.isVisible = false;

    },
    getWeather() {
      const weatherAPI = 'http://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng';
      const proxyURL = `https://thingproxy.freeboard.io/fetch/${weatherAPI}`

      fetch(proxyURL).then(response => response.text())
          // .then ((response) => console.log(response));
          .then(data => {
            // console.log(data);  //string
            let parser = new DOMParser();
            let xml = parser.parseFromString(data, "text/xml");
            let forecast = xml.getElementsByTagName('forecast');
            this.info = [];
            var today = new Date();
            //var dd = String(today.getDate()).padStart(2, '0');
            var dd = "22";
            var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
            var yyyy = today.getFullYear();

            today = yyyy + '-' + mm + '-' + dd;

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

              if (date === today) {

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
          });

    }
  },
  created() {
    this.getWeather()
  }
}
</script>


<style>
.button {
  cursor: pointer;
  font-weight: bold;
  font-size: x-large;
  margin-right: 1rem;
  margin-bottom: 1rem;
  width: 12rem;
  background-color: #15d6A0;
  justify-content: center;
  display: inline-block;
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

}
</style>

<style scoped lang="scss">

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
    background-color: #15d6A0;
    max-width: 100%;
    padding: 10px;

    visibility: hidden;
    transition: all 0.5s ease-in-out;
    max-height: 0px;
    overflow: hidden;

    &.visible {
      visibility: visible;
      max-height: 450px;
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
</style>
