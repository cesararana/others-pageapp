import React, { useEffect, useState } from 'react';
import { useForm } from "react-hook-form";
import Api from '../api';
import { Errors } from '../components';
import PageView from './PageView';

const ValidUrl = /(https?:\/\/)(www\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b([-a-zA-Z0-9()@:%_+.~#?&//=]*)/

const IDLE = "IDLE";
const SAVING = "SAVING";
const SAVED = "SAVED";

function NewPage(){
    const { formState, register, handleSubmit, formState: { errors }, reset } = useForm();
    const onSubmit = data => setState({
        ...state,
        state: SAVING,
        data,
        errors:[]
    })

    const [state,setState] = useState({
        state: IDLE,
        data: {},
        saved: {},
        errors: []
    });

    useEffect(()=>{
        async function callApi(){
            if (state.state === SAVING ) {
                // save
                let response = await Api.savePage(state.data)
                if ( response.errors ) {
                    setState({
                        ...state,
                        errors: response.errors,
                        state: IDLE
                    })
                } else {
                    reset();
                    setState({
                        ...state,
                        state: SAVED,
                        saved: response,
                        errors:[]
                    })
                }
            }
        }
        callApi();
    },[state.state, reset, state]);

    if ( state.state === SAVED ){
        return (
            <div>
                <h4>Created!</h4>
                <PageView preloadedPage={state.saved} ></PageView>
                <button onClick={()=>setState({...state, state: IDLE})}>Create another</button>
            </div>
        );
    }

    const {isDirty} = formState;
    return (
        <div className="newpage">
        <form onSubmit={handleSubmit(onSubmit)}>
        { state.errors.length > 0 &&
            <Errors errors={state.errors}></Errors>
        }
        <div className={errors.url && "hasError"}>
            {isDirty && errors.url && <Errors errors="Invalid Url"/>}  
            <label>URL:</label>
            <input  {...register("url",{required: true, pattern: ValidUrl})} />
              
        </div>
        <div className={errors.description && "hasError"}>
            {isDirty && errors.description && <Errors errors="Can't be empty" />}
        
            <label>Description:</label>
            <input {...register("description", { required: true })} />
            </div>
        <div>
            <label>Notes:</label>
            <textarea {...register("notes", { required: false })} />
        </div>
        <div>
            <button type="submit">Save</button>
        </div>
        </form>
        </div>
    );
}

export default NewPage;