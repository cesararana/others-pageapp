import React from 'react'

function fixUrl(url){
    if ( url.match(/^https?:\/\/.*/) ) {
        return url;
    }
    return "http://" + url;
}
function ExternalLink({url}) {
    return <a className="elink" href={fixUrl(url)} target="_new">{url}</a>
}

export default ExternalLink;