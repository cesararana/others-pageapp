import React, {useState, useEffect, useCallback} from "react";
import { PageItem, Loading, Errors, Paginator} from '../components'

import Api from '../api'

const IDLE = "IDLE";
const FETCHING = "FECHING";

function PageList() {

    const [state,setState] = useState({
        state: FETCHING,
        data: [],
        page: 0,
        pageSize: 10,
        totalItems: 0,
        errors: []
    });
    const changePage = (page) => setState({
        ...state,
        state: FETCHING,
        page: page
    })

    async function fetch(){
        let response = await Api.getAllPages(state.page, state.size);
        if ( response.errors ) {
            setState({
                ...state,
                state: IDLE,
                pages: [],
                errors: response.errors
            });
        } else {
            setState({
                ...state,
                data: response.data,
                state: IDLE,
                errors: [],
                totalItems: response.total,
                page: response.page
            });
        }
    }
    let callBack = useCallback(fetch,[state]);

    useEffect(()=>{
        if (state.state!==FETCHING){
            return;
        }
        callBack();
    },[state.state, callBack]);

    if ( state.state === FETCHING ) {
        return (<Loading />);
    }
    if ( state.errors.length > 0 ) {
        return <Errors errors={state.errors}></Errors>
    }
    return (
        <div className="pagelist">
            <Paginator pageChanged={changePage} totalItems={state.totalItems} currentPage={state.page} pageSize={state.pageSize}></Paginator>
            <ul>
                {
                    state.data.map( p => <li key={p.id}><PageItem {...p}></PageItem></li>)
                }
            </ul>
            <Paginator pageChanged={changePage} totalItems={state.totalItems} currentPage={state.page} pageSize={state.pageSize}></Paginator>
        </div>
    );
}

export default PageList;