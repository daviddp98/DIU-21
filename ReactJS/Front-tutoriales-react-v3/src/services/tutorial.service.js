import firebase from "../firebase";

const db = firebase.ref("/tutoriales");

class TutorialDataService {
  getAll() {
    return db;
  }
  
  create(tutorial) {
    return db.push(tutorial);
  }

  update(key, value) {
    return db.child(key).update(value);
  }

  delete(key) {
    return db.child(key).remove();
  }
  
  deleteAll() {
    return db.remove();
  }
}

export default new TutorialDataService();







/*import http from "../http-common";

class TutorialDataService {
  getAll() {
    return http.get("/findAllTutorials");
  }

  get(id) {
    return http.get(`/getTutorialByID/${id}`);
  }

  create(data) {
    return http.post("/createTutorial", data);
  }

  update(data) {
    return http.put(`/updateTutorial`, data);
  }

  delete(id) {
    return http.delete(`/deleteTutorial/${id}`);
  }

  deleteAll() {
    return http.delete(`/deleteAllTutorials`);
  }

  findByTitle(titulo) {
    return http.get(`/findByTitleContaining/${titulo}`);
  }
}

export default new TutorialDataService();*/