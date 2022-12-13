// Problem2: Bikes in a Campus (https://leetcode.com/problems/campus-bikes/)

// Time Complexity : O(mnlogmn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

/**
 * @param {number[][]} workers
 * @param {number[][]} bikes
 * @return {number[]}
 */
var assignBikes = function (workers, bikes) {
    if (workers === null || workers.length === 0 || bikes === null)
        return workers;

    let m = workers.length;
    let n = bikes.length;
    let sortedDistanceArray = [];

    for (let i = 0; i < m; i++) {
        let worker = workers[i];
        for (let j = 0; j < n; j++) {
            let bike = bikes[j];
            // Get manhattan distance for worker-bike
            let dist = getManhattanDistance(worker, bike);
            sortedDistanceArray.push([dist, i, j]);
        }
    }
    sortedDistanceArray.sort((a, b) => sortFunc(a, b));

    let result = new Array(m);
    result.fill(-1);
    let bikesArray = new Array(n);
    bikesArray.fill(false);
    let count = 0;

    for (let i = 0; i < sortedDistanceArray.length; i++) {
        let [_, w, b] = sortedDistanceArray[i];
        if (result[w] === -1 && bikesArray[b] === false) {
            bikesArray[b] = true;
            result[w] = b;
            count++;
            if (count === m)
                return result;
        }
    }
    return result;
};
var getManhattanDistance = (worker, bike) => {
    return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
};
var sortFunc = (a, b) => {
    const [distA, workerA, bikeA] = a;
    const [distB, workerB, bikeB] = b;
    return distA === distB ? (workerA === workerB ? bikeA - bikeB : workerA - workerB) : distA - distB;
}