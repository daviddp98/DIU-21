import firebase from "firebase/app";
//import * as firebase from "firebase";
import "firebase/database";

let config = {
    apiKey: "AIzaSyCMEcdgk-lurrjJIl0XKbmDIKJ2t9faGD0",
    authDomain: "tutoriales-firebase-david.firebaseapp.com",
    databaseURL: "https://tutoriales-firebase-david-default-rtdb.firebaseio.com/",
    projectId: "tutoriales-firebase-david",
    storageBucket: "tutoriales-firebase-david.appspot.com",
    messagingSenderId: "893154858574",
    appId: "1:893154858574:web:1c90d8f40370fec987151f",
    measurementId: "G-3K8K4QMLHW"
};
// Initialize Firebase
firebase.initializeApp(config);

export default firebase.database();