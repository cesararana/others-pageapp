import React, {useEffect, useState} from 'react'
import {Loading, Errors, PageItem } from '../components'

import Api from '../api'

const IDLE = "IDLE";
const FETCHING = "FECHING";


function PageView({preloadedPage, match}){
    
    let slug = (match||{params:{slug:null}}).params.slug;

    const [state,setState] = useState({
        state: slug == null ? IDLE : FETCHING,
        page: slug == null ? preloadedPage : {},
        errors:[]
    });
    const errors = state.errors;
    const page = state.page;
 
    useEffect(() =>{
        async function fetch(){
            if ( state.state !== FETCHING ){
                return;
            }
            let response = await Api.getPage(slug);
            if ( response.errors ){
                setState({
                    ...state,
                    state: IDLE,
                    errors: response.errors
                });
            } else {
                setState({
                    ...state,
                    state: IDLE,
                    errors: [],
                    page: response
                });
            }
        }
        fetch();
    },[state.state, slug, state])

    if ( state.state === FETCHING ) {
        return (<Loading />);
    }
    if ( errors.length > 0 ) {
        return <Errors errors={errors}></Errors>
    }
    return <PageItem {...page} expanded={true}></PageItem>
    
}

export default PageView;