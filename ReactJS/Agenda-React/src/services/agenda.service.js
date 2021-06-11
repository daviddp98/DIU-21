import firebase from "../firebase";

const db = firebase.ref("/contactos");

class AgendaDataService {

  getAll() {
    return db;
  }

  create(contacto) {
    return db.push(contacto);
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

export default new AgendaDataService();