// import AuctionService from '../services/auction.service';

const user = JSON.parse(localStorage.getItem('user'));
const initialState = user
    ? { status: { loggedIn: true }, user }
    : { status: { loggedIn: false }, user: null };

export const auctions = {
    namespaced: true,
    state: initialState,
    actions: {

    },
    mutations: {

    }
};
