import React, { Component } from 'react';
import ReactTable from 'react-table-6';
import 'react-table-6/react-table.css';
import AddCake from './AddCake';

class CakeApp extends Component {

    constructor(props) {
        super(props)
        this.state = {
            cakes : []
        };
    }

    fetchCakes = () => {
        const url = [process.env.REACT_APP_CAKE_API_ENDPOINT,'cakes/'].join('');
        fetch(url)
        .then(response => response.json())
        .then(responseData => this.setState({cakes:responseData}))
        .catch(err=>console.error(err));
    }

    addCake(cake) {
        const url = [process.env.REACT_APP_CAKE_API_ENDPOINT,'cakes/'].join('');
        fetch(url,{
            method: 'POST',
            headers: {
                'Content-Type':'application/json',
            },
            body:JSON.stringify(cake)
        })
        .then(res=>this.fetchCakes())
        .catch(err=>console.error(err));
    }

    componentDidMount() {
        this.fetchCakes();
    }

    render() {
        const columns = [
            {Header: "Image",
                  Cell: (cake) => {
                    return <img height={50} width={50} src={cake.original.imageURL} alt={cake.original.description}/>
                  }},
            {Header:'Name', accessor: 'name'},
            {Header:'Description', accessor: 'description'},
        ];
        return(
            <div className="Cakes">
               <ReactTable data={this.state.cakes} columns={columns} minRows={0} />
               <AddCake addCake={this.addCake} fetchCakes={this.fetchCakes} />
            </div>
        );
    }

}

export default CakeApp;
