import * as firebase from "firebase";
import "firebase/database";

let config = {
  apiKey: "AIzaSyB_IRX6wqjWyfPKgcU5FWOyUT7KxH0Qsko",
  authDomain: "agenda-192af.firebaseapp.com",
  databaseURL: "https://agenda-192af-default-rtdb.firebaseio.com/",
  projectId: "agenda-192af",
  storageBucket: "agenda-192af.appspot.com",
  messagingSenderId: "98020158090",
  appId: "1:98020158090:web:ef28c478d5c60ccf901b73"
};

firebase.initializeApp(config);

export default firebase.database();
