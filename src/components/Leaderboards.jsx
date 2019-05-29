import React, { useState } from 'react';

import InputAction from './InputAction';
import Leaderboard from './Leaderboard';

export default props => {
    return (
        <div id="leaderboards">
            <h2>LEADERBOARDS</h2>
            <InputAction
                id="leaderboards-by-user"
                buttonText="Get Leaderboards"
                disabled={() => false}
                action={props.getLeaderboards}
            />
            {props.leaderboards
                .filter(leaderboard => leaderboard.results.length)
                .map((leaderboard, i) =>
                    <Leaderboard
                        key={i}
                        code={leaderboard.code}
                        leaderboard={leaderboard.results}
                    />
                )
            }
        </div>
    );
}
