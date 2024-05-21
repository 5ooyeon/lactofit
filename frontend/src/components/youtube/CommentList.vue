<template>
    <div>
        <img :src="comment.snippet.topLevelComment.snippet.authorProfileImageUrl" alt="Profile image" class="mr-2 rounded">
        <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
          <strong class="d-block text-gray-dark">{{ comment.snippet.topLevelComment.snippet.authorDisplayName }}</strong>
          <p>{{ comment.snippet.topLevelComment.snippet.publishedAt }}</p>
          {{ comment.snippet.topLevelComment.snippet.textOriginal }}
        </p>

    </div>
  </template>
  
<script setup>

const props = defineProps({
  comment: {
    type: Object,
    required: true
  }
})

//   export default {
//     name: 'CommentList',
//     props: {
//       comments: {
//         type: Object,
//         required: true
//       }
//     }
//   };
</script>