import React from 'react';
import axios from 'axios';
import {Link} from 'react-router-dom';
import { PORT_NUMBER } from './config';


class AddPatient extends React.Component{
 //added code:
 constructor(props) {
    super(props);
    this.state = {
      firstName: '',
      lastName: '',
      age: '',
      errorMessage: '',
    };
  }

    handleSubmit(event){
        event.preventDefault();
        const data = {
            firstName:this.state.firstName,
            lastName:this.state.lastName,
            age:this.state.age
        }
        console.log(data);
        axios.post(`http://localhost:${PORT_NUMBER}/clinicalservices/api/patients`,data)
        .then(res=>{
          //  document.write("Patient Created Successfully!!");
          console.log(res);
        this.props.history.push('/'); // redirect to the patients page
      }).catch((error) => {
        console.log(error);
        this.setState({ errorMessage: 'Failed to create patient' });
        });
    }

    render(){
        return (<div>
                <h2>Create Patient:</h2>
                <form>
                First Name:<input type="text" name="firstName" onChange={(event)=>{this.setState({firstName:event.target.value});}} align="left"/>
                Last Name:<input type="text" name="lastName" onChange={(event)=>{this.setState({lastName:event.target.value})}} align="left"/>
                Age:<input type="text" name="age" onChange={(event)=>{this.setState({age:event.target.value})}} align="left"/>
                <button onClick={this.handleSubmit.bind(this)}>Confirm</button>
                </form>
                {/*added code below: */}
                {this.state.errorMessage && <p>{this.state.errorMessage}</p>}
                <Link  to={'/'}>Go Back</Link>
        </div>)
    }
}

export default AddPatient;






