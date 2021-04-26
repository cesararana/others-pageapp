import React from 'react'
import lodash  from 'lodash'

function Errors({errors = []}){

    if ( lodash.isArray(errors) ) {
        errors = errors.filter( e=>!lodash.isNull(e))
    } 
    else if ( lodash.isString(errors) ) {
        errors = [ errors ]
    }
    else if ( lodash.isObject(errors) ) {
        errors = [ errors.message ];
    }

    if (errors && errors.length > 0) {
        return (
        <ul className="errors">
            { errors.map( e => <li>{e}</li>)}
        </ul>
        );
    }
    return (<></>);
}

export default Errors;