import React from 'react';

function Paginator({pageChanged, totalItems, currentPage, pageSize}){

    if ( totalItems <= 0 ) {
        return (<></>);
    }

    const totalPages = Math.ceil(totalItems / pageSize);
    const goPrev = () => pageChanged(currentPage-1);
    const goNext = () => pageChanged(currentPage+1);

    const prev = currentPage <= 0 ?
                 <span /> :
                 <button onClick={goPrev}>{"<"}</button>;
    const next = currentPage < totalPages - 1 ?
                 
                 <button onClick={goNext}>{">"}</button> :
                 <span />;

    return (
        <div className="paginator">
            {prev}
            <span>{currentPage+1} / {totalPages} ({totalItems})</span>
            {next}
        </div>
    );
}

export default Paginator;