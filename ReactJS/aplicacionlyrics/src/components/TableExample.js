import React, { Component } from 'react';
import { Table } from 'react-bootstrap';

class Table_Example extends Component {
    constructor() {
        super()
    }

    renderData(data) {
        return (
            <tr>
                <td>{data}</td>
            </tr>
        )
    }

    render() {
        return (
            <Table responsive striped bordered hover size="sm">
                <thead>
                    <tr>
                        <th>Letra</th>
                    </tr>
                </thead>
                <tbody>
                    {this.props.data.map(this.renderData)}
                </tbody>
            </Table>
        )
    }
}

export default Table_Example;