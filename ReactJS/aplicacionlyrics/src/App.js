import React, { Component } from 'react';
import { Row, Col, Container } from 'react-bootstrap';
import Table_Example from './components/TableExample';
import Form_Example from './components/FormExample';

class App extends Component {
  constructor() {
    super()
    this.state = {
      data: [], //Contiene todas las filas de la tabla
      grupo: '', //Contenido caja de texto del nombre del grupo
      cancion: '', //Contenido caja de texto del nombre de la cancion
      letra: ''//Contenido caja de texto de la letra de la cancion
    }
  }

  passParams = (data) => {
    let dataNew = this.state.data; //let declara variables de ámbito local
    dataNew.push(data) //El método push anexa nuevos elementos a un array
    this.setState({
      data: dataNew
    });
  }

     
    /*fetch(generarURL(this.grupo, this.cancion))
        .then((response)=> {          
          return response.json();         
        })
        .then((data)=> {
            this.setState({
              data: data
            });
        })
        .catch(function(err) {
            console.error(err);
        });*/
  

  render() {
    return (
      <Container>
        <Row>
          <Col>
            <Form_Example passParams={this.passParams} />
          </Col>
        </Row>
        <Row>
          <Col>
            <Table_Example data={this.state.data} />
          </Col>
        </Row>
      </Container>
    );
  }
}

export default App;