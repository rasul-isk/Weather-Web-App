<template>
  <div class="page-loader" v-if="!isloaded">
    <div class="cube"><h2>L</h2></div>
    <div class="cube"><h2>O</h2></div>
    <div class="cube"><h2>A</h2></div>
    <div class="cube"><h2>D</h2></div>
    <div class="cube"><h2>I</h2></div>
    <div class="cube"><h2>N</h2></div>
    <div class="cube"><h2>G</h2></div>
  </div>

</template>

<script>
export default {
  data: () => {
    return {
      isloaded: false
    }
  },
  mounted() {
    setTimeout(() => (this.isloaded = true), 3500);
  },
}
</script>

<style lang="scss" scoped>
$colors: #8CC271, #69BEEB, #F5AA39, #E9643B, #15d6a0, #9368B7, #FFC425, #9368B7;
//$colors: #15d6a0, #FFFFFF, #FFC425, #9368B7;

// -----------------------------------------------------
h1 {

}

.page-loader {
  display: flex;
  justify-content: center;
  text-align: center;
  align-items: center;
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: #333;
  z-index: 999;
}

// -----------------------------------------------------

.cube {
  width: 40px;
  height: 40px;
  margin-right: 10px;

  @for $i from 1 through length($colors) {
    &:nth-child(#{$i}) {
      background-color: nth($colors, $i);
    }
  }

  &:first-child {
    animation: left 0.8s infinite;
  }

  &:last-child {
    animation: right 0.8s infinite .4s;
  }
}

// -----------------------------------------------------

@keyframes left {
  40% {
    transform: translateX(-60px);
  }
  50% {
    transform: translateX(0);
  }
}

@keyframes right {
  40% {
    transform: translateX(60px);
  }
  50% {
    transform: translateX(0);
  }
}
</style>