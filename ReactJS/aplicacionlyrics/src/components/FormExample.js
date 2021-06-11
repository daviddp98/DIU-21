import React, { Component } from 'react';
import { Form, Button, Row, Col, FormGroup } from 'react-bootstrap';


function generarURL(grupo, cancion) {
    var url = 'https://api.lyrics.ovh/v1/' + grupo + '/' + cancion;

    return url;
}

class Form_Example extends Component {
    constructor() {
        super()
        this.state = {
            grupo: '',
            cancion: '',
        }
    }

    //Método que gestiona cuando se produce un cambio en algún campo del formulario
    //Obtiene la info del evento y actualiza el estado
    //La actualización del estado, implica la llamada al método render()
    handleChange = event => {
        const name = event.target.name;
        const value = event.target.value;
        this.setState({ [name]: value });
    }

    //Método que gestiona la pulsación del botón
    handleSubmit = event => {
        event.preventDefault();//paramos la ejecución por  del evento por defecto. 
        //Esto de utiliza para que no se proceda con el submit hasta asegurarnos de que se han enviado los datos correctamente.
        fetch(generarURL(this.grupo, this.cancion), {
            method: 'GET', //Recibimos información
            headers: {
                "Content-type": "application/json;"
            }
        })
            .then((response) => { //Primera "Promise"
                return response.text();
            })
            .then(data => { //Segunda "Promise"
                this.props.passParams(data); //Callback al padre. En el componente App
                //hemos especificado que el método passParams del Form es el mismo
                //que el método passParams del padre. Por lo tanto la invocación de este método
                //invocará el método del padre, que básicamente actualiza su estado (el del padre), y como consecuencia
                //se llama al método render() (del padre), que dibuja la tabla actualizada
            })
           /* var request = new Request(generarURL(this.grupo, this.cancion), {
                method: 'GET',
                mode: 'cors',
                credentials: 'omit',
                cache: 'only-if-cached',
                referrerPolicy: 'no-referrer'
            });
            fetch(request)
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
    }

    render() {
        return (
            <Form onSubmit={this.handleSubmit}>
                <Row>
                    <Col>
                        <Form.Group>
                            <Form.Label>Grupo </Form.Label>
                            <Form.Control required min="1" placeholder="Grupo/Cantante" name="grupo" value={this.state.grupo} onChange={this.handleChange} />
                        </Form.Group>
                    </Col>
                    <Col>
                        <Form.Group>
                            <Form.Label>Canción </Form.Label>
                            <Form.Control required min="1" placeholder="Título de la canción" name="cancion" value={this.state.cancion} onChange={this.handleChange} />
                        </Form.Group>
                    </Col>
                </Row>
                <Row>
                    <FormGroup>
                        <Button type="submit">Buscar</Button>
                    </FormGroup>
                </Row>
            </Form>
        )
    }
}

export default Form_Example;