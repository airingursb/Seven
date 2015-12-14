//
//  ShowMyScoresController.swift
//  seven
//
//  Created by Airing on 15/11/7.
//  Copyright © 2015年 Airing. All rights reserved.
//

import UIKit
import JSONJoy
import SwiftHTTP

class ShowMyScoresController: UIViewController {
    
    struct ResponseData : JSONJoy {
        var result: Int?
        var totalScore: Double?
        init(_ decoder: JSONDecoder) {
            result = decoder["result"].integer
            totalScore = decoder["totalScore"].double
        }
    }

    
    var stuId:Int?
    
    @IBOutlet weak var lblMyScores: UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func showMyScores() {
        let request = HTTPTask()
        let params: Dictionary<String,AnyObject> = ["studentId": stuId!]
        request.POST("http://localhost:8080/seven/show_my_scores.action", parameters: params, completionHandler: {(response: HTTPResponse) in
            if let res: AnyObject = response.responseObject {
                let data = ResponseData(JSONDecoder(res))
                print("result:\(data.result!)")
                if (data.result! == 1) {
                    print("showMyScores secceed")
                    NSOperationQueue.mainQueue().addOperationWithBlock({ () -> Void in
                        self.lblMyScores.text = String(data.totalScore!)
                    })
                } else {
                    print("showMyScores fail")
                }
            }
        })

    }
}
