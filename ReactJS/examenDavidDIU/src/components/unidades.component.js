import React, { Component } from "react";
const estiloColor = { color: 'red' };

export default class Unidades extends Component {
    constructor(props) {
        super(props);

    }

    render() {
        return (
            <div className="list row">
                <div className="col-md-6">

                    {this.props.stock >= 10 ? (
                        <h6>Unidades ok</h6>
                    ) : (
                        <div className="col-md-6">
                            <div>
                                <h8 style={estiloColor}>Unidades Bajas</h8>
                            </div>
                            <div>
                                <button className="badge badge-danger mr-2"
                                    onClick={this.props.actualizar(this.props.stock + 10)}>Pedir</button>
                            </div>
                        </div>
                    )}
                </div>
            </div>
        );
    }
}