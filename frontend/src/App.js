import './App.css';

import React from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  NavLink
} from "react-router-dom";
import { newPageRoute, pageListRoute, pageViewRoute } from './routers'
import PageList from './pages/PageList'
import NewPage from './pages/NewPage'
import PageView from './pages/PageView'

const ALLPAGES = pageListRoute();
const NEWPAGE = newPageRoute();

export default function App() {
  return (
    <Router>
      <div className="App">
        <nav className="nav">
          <ul>
            <li>
              <NavLink activeClassName="activePage" exact to={ALLPAGES}>All Pages</NavLink>
            </li>
            <li>
              <NavLink activeClassName="activePage" exact to={NEWPAGE}>New Page</NavLink>
            </li>
          </ul>
        </nav>
        <Switch>
          <Route path={NEWPAGE}>
            <NewPage />
          </Route>
          <Route path={ALLPAGES} exact={true}>
            <PageList />
          </Route>
          <Route path={pageViewRoute(":slug")} exact={true} component={PageView} />
        </Switch>
      </div>
    </Router>
  );
}

