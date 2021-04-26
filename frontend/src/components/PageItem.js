import React from 'react'
import {Link} from "react-router-dom";
import { pageViewRoute } from '../routers'
import {ExternalLink} from '../components'

function Page({slug, description, notes, url, expanded}){
    return (
        <div className="pageitem">
            <ExternalLink url={url} />
            <span>{description}</span>
            {
                !expanded && <Link to={pageViewRoute(slug)}>...more</Link>
            }
            { expanded && notes !== "" &&
                <pre>Notes: {notes}</pre> 
            }
        </div>
    );
}

export default Page;