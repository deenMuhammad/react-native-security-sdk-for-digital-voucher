require "json"
package = JSON.parse(File.read(File.join(__dir__, "./../package.json")))

Pod::Spec.new do |s|
  s.name         = "RNNativeSecuritySdkForDigitalVoucher"
  s.version      = package["version"]
  s.summary      = "RNNativeSecuritySdkForDigitalVoucher"
  s.description  = <<-DESC
                  RNNativeSecuritySdkForDigitalVoucher
                   DESC
  s.homepage     = "https://github.com/deenMuhammad/react-native-security-sdk-for-digital-voucher.git"
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "author@domain.cn" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/author/RNNativeSecuritySdkForDigitalVoucher.git", :tag => "master" }
  s.source_files  = "RNNativeSecuritySdkForDigitalVoucher/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

  
