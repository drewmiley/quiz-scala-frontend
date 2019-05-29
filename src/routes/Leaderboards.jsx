import React from 'react';

import Leaderboards from '../components/Leaderboards';

export default props => {
    return <>
        <Leaderboards
            getLeaderboards={props.getLeaderboards}
            leaderboards={props.leaderboards}
        />
    </>
};
