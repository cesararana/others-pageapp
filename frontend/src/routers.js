
function pageViewRoute(slug) {
    return `/page/${slug}`;
}
function pageListRoute() {
    return "/";
}
function newPageRoute() {
    return "/newpage";
}


export {
    pageViewRoute,
    pageListRoute,
    newPageRoute
};